# Chotu User Guide

Chotu is a simple command-line chatbot for managing personal tasks quickly.

## What Chotu Can Do

- Add todos, deadlines, and events
- List all tasks
- Mark and unmark tasks
- Delete tasks
- Find tasks by keyword
- Save tasks automatically

## Quick Start

1. Run `Chotu.java`.
2. Choose menu option `2` for task manager mode.
3. Enter commands shown below.
4. Type `bye` to exit task manager mode.

## Command Summary

| Command | Format | Example |
| --- | --- | --- |
| List tasks | `list` | `list` |
| Add todo | `todo DESCRIPTION` | `todo read chapter 5` |
| Add deadline | `deadline DESCRIPTION /by yyyy-MM-dd` | `deadline submit iP /by 2026-03-20` |
| Add event | `event DESCRIPTION /from START /to END` | `event hackathon /from Fri 8pm /to Sat 6am` |
| Mark done | `mark TASK_NUMBER` | `mark 2` |
| Mark not done | `unmark TASK_NUMBER` | `unmark 2` |
| Delete task | `delete TASK_NUMBER` | `delete 3` |
| Find tasks | `find KEYWORD` | `find book` |
| Exit | `bye` | `bye` |

## Features

### 1) Add a Todo

Use this when you only need a description.

`todo DESCRIPTION`

Example:

```text
todo revise arrays
```

### 2) Add a Deadline

Use this when a task has a due date.

`deadline DESCRIPTION /by yyyy-MM-dd`

Example:

```text
deadline return library book /by 2026-03-12
```

Note:
- Date must be in `yyyy-MM-dd` format.
- Chotu displays deadline dates in a friendlier format (e.g., `Mar 12 2026`).

### 3) Add an Event

Use this when a task has a start and end detail.

`event DESCRIPTION /from START /to END`

Example:

```text
event team sync /from Monday 10am /to Monday 11am
```

### 4) List Tasks

Shows all current tasks with their task numbers.

`list`

### 5) Mark and Unmark Tasks

Mark a task as done:

`mark TASK_NUMBER`

Unmark a task:

`unmark TASK_NUMBER`

Example:

```text
mark 1
unmark 1
```

### 6) Delete a Task

Deletes a task using its number.

`delete TASK_NUMBER`

Example:

```text
delete 2
```

### 7) Find Tasks

Finds tasks whose descriptions contain a keyword (case-insensitive).

`find KEYWORD`

Example:

```text
find book
```

### 8) Save Behavior

Chotu saves changes automatically after add, mark, unmark, and delete actions.
Saved tasks are loaded again when the app starts.

## Tips

- Use `list` if you forget task numbers.
- Keep deadline dates in `yyyy-MM-dd`.
- If a command is invalid, Chotu will show an error and wait for the next command.
