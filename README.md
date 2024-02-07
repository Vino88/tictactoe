# Tic Tac Toe

A secured web app to play Tic Tac Toe against a dummy computer opponent.

## Features & Notes
* Play a game on a default 3x3 configurable board with an option to go first or after the computer opponent.
* Computer opponent's AI chooses random squares, except when going first in which case the center tile is always picked.
* User game data is persisted to an in-memory database. As long as the server is not restarted, a player can leave and return to finish an in-progress game.  
* App is secured with a username & password login. Database is seeded with two usernames `Vino88` or `Anonymous_User`. Both have the same password `password123`.
* UI renders each time through a full page refresh in the name of simplicity.

## Tech Stack
| | Technology |
|---|---|
| __Language__ | Java 8 |
| __Framework__ | Spring Boot (v2.7.4) |
| __Data Layer__ | H2 Database, JPA & Hibernate | 
| __UI Layer__ | HTML, CSS, Javascript, jQuery (v3.6), [Bootstrap](https://getbootstrap.com/) (v5), [Thymeleaf](http://www.thymeleaf.org/) |
| __Testing__ | JUnit 5 |
| __Build Tool__ | Gradle |

## Install & Run
* Install Java 8.
* Clone repo: `git clone https://github.com/Vino88/tictactoe.git`
* Run Springboot Application
* Once app is running, go to [http://localhost:8090/tictactoe/](http://localhost:8090/tictactoe/).
* Log in with username `Vino88` or `Anonymous_User` and password `password123` to play a game.