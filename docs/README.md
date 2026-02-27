# Bill User Guide
Bill is a lightweight, CLI-based (Command Line Interface) assistant designed to help you  
track your daily tasks, deadlines, and events

## Getting Started
To start the application, run the following command in your terminal:
`java Bill`

```
Hello! I'm Bill
What can I do for you?
```

## Add a task
Bill has 3 different types of tasks:
1. Todos: `todo <desc>`
2. Deadlines: `deadline <desc> /by <dd/mm/yyyy HHmm>`
3. Event: `event <desc> /from <dd/mm/yyyy HHmm> /to <dd/mm/yyyy HHmm>`

```
Got it. I've added this task:
[T][ ] test 1
```
```
Got it. I've added this task:
[D][ ] Return book (by: Dec 02 2026, 06:00 PM)
```
```
Got it. I've added this task:
[E][ ] Party (from: Jan 01 2026, 08:00 PM to: Jan 01 2026, 11:59 PM)
```


## List
Bill can provide a list of tasks with `list` \
The list will include two icons for each task
1. The icon with [E], [D], [T] is used to indicate the type of task
2. The blank icon [ ] and [X] is used to indicate if the task is complete

```
Here are the tasks in your list:
1.[E][ ] test (from: Dec 02 2019, 06:00 PM to: Dec 02 2019, 06:00 PM)
2.[T][ ] test 1
3.[D][ ] Return book (by: Dec 02 2026, 06:00 PM)
4.[E][ ] Party (from: Jan 01 2026, 08:00 PM to: Jan 01 2026, 11:59 PM)
```

## Marking
Bill allows tasks to be marked and unmarked indicating completion status\
Example: `mark <index>` or `unmark <index>`

```
Nice! I've marked this task as done:
  [E][X] test (from: Dec 02 2019, 06:00 PM to: Dec 02 2019, 06:00 PM)
```
```
OK, I've marked this task as not done yet:
  [E][ ] test (from: Dec 02 2019, 06:00 PM to: Dec 02 2019, 06:00 PM)
```

## Delete
Bill allows tasks to be deleted\
Example: `delete <index>`

```
Noted. I've removed this task:
 [E][ ] test (from: Dec 02 2019, 06:00 PM to: Dec 02 2019, 06:00 PM)
```

## Find
Bill supports finding strings within the tasks' description.

Example: `find <keyword>`

```
Here are the matching tasks in your list:
1.[T][ ] test 1
```

## Exit
To exit the program, enter the following command:
`bye`

```
Tasks saved successfully.
Bye. Hope to see you again soon!
```

## Help
Due to the incomplete nature of Bill's manual, you should type an unknown command to see the
list of currently implemented commands. \
However, do note that `help` and `man` are in use as placeholders for the completed manual.

## Saving
Bill has an autosave system that saves the list of tasks from the previous session.  
This system automatically loads tasks from the previous session upon opening Bill.