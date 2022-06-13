
# Notable Features in JetBrains IDEs (with Shortcuts!)

An incomplete compilation


## The One Shortcut You Must Learn

| Win/Linux      | Mac     | Name        |
|----------------|---------|-------------|
| `Ctrl+Shift+A` | `⇧ ⌘ A` | Find Action |

**Why I need it**  
Every action of the IDE is available here through typing instead of through clicking. This also makes the IDE very discoverable: even if I do not know what the action is called, I can type something and see if the search yields something useful. This is also a great way to look up keyboard shortcuts.


## Navigate IDE

| Win/Linux           | Mac             | Name                                                    |
|---------------------|-----------------|---------------------------------------------------------|
| `Alt+1`, `Alt+2`, … | `⌘ 1`, `⌘ 2`, … | Focus tool window (1 – Project, 3 – Search, 9 – VCS, …) |
| `Escape`            | `⎋`             | Focus the editor                                        |
| `Shift+Escape`      | `⇧⎋`            | Close last focused tool window                          |


## Navigate Code

| Win/Linux      | Mac     | Name                                          | Notes                                    |
|----------------|---------|-----------------------------------------------|------------------------------------------|
| `Ctrl+B`       | `⌘ B`   | Go to Declaration <br> _and_ <br> Find Usages |                                          |
| `Ctrl+Shift+N` | `⇧ ⌘ O` | Go to File                                    | Type path segments to narrow down search |
| `Ctrl+E`       | `⌘ E`   | Recent Files                                  | Hit it 2×: Recently Changed Files        |
| `Ctrl+Shift+F` | `⇧ ⌘ F` | Find in Files                                 |


## Edit Code

| Win/Linux                | Mac           | Name                    | Notes                                                                                                                                                         |
|--------------------------|---------------|-------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Ctrl+/`                 | `⌘ /`         | Toggle Line Comment     | Works with multiple lines too. Iirc, does not work with German keyboard layouts out of the box. I recommend remapping `Ctrl+Shift+7`/`⇧ ⌘ 7` in the Settings. |
| `Ctrl+W`/ `Ctrl+Shift+W` | `⌥ ↑` / `⌥ ↓` | Extend/Shrink Selection | Greatly reduces structural editing errors                                                                                                                     |
| `Alt+Enter`              | `⌥ ⏎`         | Show Context Actions    | Hit it from time to time, IntelliJ may surprise you                                                                                                           |
| `Ctrl+Alt+T`             | `⌥ ⌘ T`       | Surround With …         | … `if/else`, `for`, `try/catch`                                                                                                                               |


## Refactor

| Win/Linux          | Mac     | Name                   | Notes                          |
|--------------------|---------|------------------------|--------------------------------|
| `Ctrl+Alt+Shift+T` | `⌃ T`   | Refactor This          | List of available refactorings |
| `Shift+F6`         | `⇧ F6`  | Rename                 |
| `Ctrl+Alt+V`       | `⌥ ⌘ V` | Introduce Variable     |
| `Ctrl+Alt+M`       | `⌥ ⌘ M` | Extract Method         |
| `Ctrl+Alt+N`       | `⌥ ⌘ N` | Inline Variable/Method |


## VCS

| Win/Linux    | Mac     | Name                       | Notes                                                                                                                                   |
|--------------|---------|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| `Ctrl+K`     | `⌘ K`   | Commit                     | IMO, the older modal commit dialog is far superior to Git CLI.                                                                          |
| `Ctrl+Alt+Z` | `⌥ ⌘ Z` | Rollback                   | Reverts contiguous changed lines when hit while cursor on a changed line. <br> Otherwise, opens dialog to select which files to revert. |
| –            | –       | Show History for Selection | Understand how a code section has changed over time                                                                                     |


## Run

| Win/Linux        | Mac     | Name                                                         | Notes                                                                                                                                                    |
|------------------|---------|--------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Shift+F10`      | `⌃ R`   | <official label rubbish; my suggestion: Re-Run>              | Re-run what you ran last                                                                                                                                 |
| `Ctrl+Shift+F10` | `⌃ ⌘ R` | <official label rubbish; my suggestion: Run Current Scope>   | When cursor within test method, only runs test method. When cursor within test class, runs whole class, etc.                                             |
| `Alt+Shift+F10`  | `⌃ ⇧ R` | <official label rubbish; my suggestion: Run Selector>        | Save commonly used run configurations and run them easily from here. Typical run configs: different test suites, e.g unit tests, integration tests, etc. |
