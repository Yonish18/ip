import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles loading tasks from disk and saving tasks to disk.
 */
public class Storage {
    private final Path dataDir;
    private final Path dataFile;

    /**
     * Creates a storage object for the given file path.
     *
     * @param filePath path to the data file
     */
    public Storage(String filePath) {
        this.dataFile = Paths.get(filePath);
        Path parent = this.dataFile.getParent();
        this.dataDir = parent == null ? Paths.get(".") : parent;
    }

    /**
     * Loads tasks from the file into the given task list.
     * Invalid lines are skipped.
     *
     * @param tasks target task list
     * @param ui UI used to show warning messsages
     */
    public void loadTasks(ArrayList<Task> tasks, Ui ui) {
        if (!Files.exists(dataFile)) {
            return;
        }
        int skipped = 0;
        try {
            for (String line : Files.readAllLines(dataFile)) {
                if (line == null) {
                    continue;
                }
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                String[] parts = trimmed.split("\\s*\\|\\s*");
                if (parts.length < 3) {
                    skipped++;
                    continue;
                }
                String type = parts[0];
                String doneFlag = parts[1];
                String description = parts[2];
                if (description.isEmpty()) {
                    skipped++;
                    continue;
                }

                Task task;
                try {
                    if (type.equals("T")) {
                        task = new Todo(description);
                    } else if (type.equals("D")) {
                        if (parts.length < 4) {
                            skipped++;
                            continue;
                        }
                        task = new Deadline(description, parts[3]);
                    } else if (type.equals("E")) {
                        if (parts.length < 5) {
                            skipped++;
                            continue;
                        }
                        task = new Event(description, parts[3], parts[4]);
                    } else {
                        skipped++;
                        continue;
                    }
                } catch (ChotuException e) {
                    skipped++;
                    continue;
                }

                if (doneFlag.equals("1")) {
                    task.setDone(true);
                } else if (doneFlag.equals("0")) {
                    task.setDone(false);
                } else {
                    skipped++;
                    continue;
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            ui.show(Ui.DIVIDER + "Warning: I couldn't read saved tasks.\n" + Ui.DIVIDER);
            return;
        }

        if (skipped > 0) {
            ui.show(Ui.DIVIDER + "Warning: I skipped " + skipped + " corrupted task line(s) in saved data.\n" + Ui.DIVIDER);
        }
    }

    /**
     * Saves all tasks to the data file.
     *
     * @param tasks tasks to save
     * @param ui UI used to show warnings if saving fails
     */
    public void saveTasks(ArrayList<Task> tasks, Ui ui) {
        try {
            Files.createDirectories(dataDir);
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                content.append(serializeTask(task));
                if (i < tasks.size() - 1) {
                    content.append(System.lineSeparator());
                }
            }
            Files.write(dataFile, content.toString().getBytes());
        } catch (IOException e) {
            ui.show(Ui.DIVIDER + "Warning: I couldn't save your tasks.\n" + Ui.DIVIDER);
        }
    }

    /**
     * Converts one task to a single line for file storage.
     *
     * @param task task to serialize
     * @return storage line
     */
    private String serializeTask(Task task) {
        String doneFlag = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + doneFlag + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneFlag + " | " + deadline.getDescription() + " | " + deadline.getByForStorage();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneFlag + " | " + event.getDescription() + " | " + event.from + " | " + event.to;
        }
        return "T | " + doneFlag + " | " + task.getDescription();
    }
}
