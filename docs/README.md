# BenjaminBot

Welcome to BenjaminBot, a bot designed to help you keep track of your tasks.

## User Guide
>[!IMPORTANT]
> For the commands below, any *italicised* words are meant to be replaced by you!

### Adding Todos
Todos are any tasks that do not have a deadline.\
Command: 'todo *description*'\
E.g. 'todo clean room'

### Adding Deadlines
Deadlines are any tasks that have a end timing.\
Command: 'deadline *description* /by *YYYY-MM-DDTHH:MM:SS*'\
E.g. deadline homework /by 2025-02-01T14:00:00

### Adding Events
Events are any tasks that have a start and end timing.\
Command: 'event *description* /from *YYYY-MM-DDTHH:MM:SS* /to *YYYY-MM-DDTHH:MM:SS*'\
E.g. event tutorial /from 2025-02-01T13:00:00 /by 2025-02-01T14:00:00

### List Tasks
This command lists all the tasks that you have saved to BenjaminBot!\
Command: 'list'

### Mark Tasks
Marks a task as done. The number below must correspond to the task number given by the 'list' command.\
Command: 'mark *number*'\
E.g. 'mark 5'

### Unmark Tasks
Marks a task as not done. The number below must correspond to the task number given by the 'list' command.\
Command: 'unmark *number*'\
E.g. 'unmark 5'

### Delete Tasks
Deletes a task. The number below must correspond to the task number given by the 'list' command.\
Command: 'delete *number*'\
E.g. 'delete 5'

### Find Tasks
Finds all tasks where the task description matches the string input.\
Command: 'find *input*'\
E.g. 'find dog'

### View Schedule
Returns the schedule of the day given by user input. Shows all events and deadlines for that day.\
Command: 'view *YYYY-MM-DD*'\
E.g. 'view 2025-02-01'

### Close Bot
Saves your current lists of tasks and exits the program. Remember to come back!
Command: 'bye'