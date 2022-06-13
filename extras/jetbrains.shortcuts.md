
# Notable Features in JetBrains IDEs (with Shortcuts!)

An incomplete compilation


## The One Shortcut You Must Learn

| Win/Linux      | Mac     | Name        |
|----------------|---------|-------------|
| `Ctrl+Shift+A` | `⇧ ⌘ A` | Find Action |

**Why I need it**  
Every action of the IDE is available here through typing instead of through clicking. This also makes the IDE very discoverable: even if I do not know what the action is called, I can type something and see if the search yields something useful.


## Navigate IDE

| Win/Linux | Mac             | Name                                                    |
|-----------|-----------------|---------------------------------------------------------|
|           | `⌘ 1`, `⌘ 2`, … | Focus tool window (1 – Project, 3 – Search, 9 – VCS, …) |
|           | `⎋`             | Focus the editor                                        |
|           | `⇧⎋`            | Close last focused tool window                          |


## Navigate Code

| Win/Linux | Mac     | Name                                          | Notes                                    |
|-----------|---------|-----------------------------------------------|------------------------------------------|
|           | `⌘ B`   | Go to Declaration <br> _and_ <br> Find Usages |                                          |
|           | `⇧ ⌘ O` | Go to File                                    | Type path segments to narrow down search |
|           | `⌘ E`   | Recent Files                                  | Hit it 2×: Recently Changed Files        |


## Edit Code

| Win/Linux | Mac           | Name                    | Notes                                               |
|-----------|---------------|-------------------------|-----------------------------------------------------|
|           | `⌥ ↑` / `⌥ ↓` | Extend/Shrink Selection | Greatly reduces structural editing errors           |
|           | `⌥ ⏎`         | Show Context Actions    | Hit it from time to time, IntelliJ may surprise you |
|           | `⌥ ⌘ T`       | Surround With …         | … `if/else`, `for`, `try/catch`                     |


## Refactor

| Win/Linux | Mac     | Name                   | Notes                          |
|-----------|---------|------------------------|--------------------------------|
|           | `⌃ T`   | Refactor This          | List of available refactorings |
|           | `⇧ F6`  | Rename                 |
|           | `⌥ ⌘ V` | Introduce Variable     |
|           | `⌥ ⌘ M` | Extract Method         |
|           | `⌥ ⌘ N` | Inline Variable/Method |


## VCS

| Win/Linux | Mac     | Name                       | Notes                                                                                                                                   |
|-----------|---------|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
|           | `⌘ K`   | Commit                     | IMO, the older modal commit dialog is far superior to Git CLI.                                                                          |
|           | `⌥ ⌘ Z` | Rollback                   | Reverts contiguous changed lines when hit while cursor on a changed line. <br> Otherwise, opens dialog to select which files to revert. |
| –         | –       | Show History for Selection | Understand how a code section has changed over time                                                                                     |
