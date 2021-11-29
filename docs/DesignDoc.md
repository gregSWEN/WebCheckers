---
geometry: margin=1in
---
# PROJECT Design Documentation

## Team Information
* Team name: thePurpleNarwhals
* Team members
  * Michael Taylor
  * Greg Villafane
  * Huan Huynh
  * Andrew Chacon

## Executive Summary
The application must allow players to play checkers with other players who are currently signed in.
The game user interface (UI) will support a game experience using drag-and-drop browser capabilities
for making moves. Beyond this minimal set of features, we have grand vision for how we could further enhance
the player experience with some additional features beyond the basic checkers game.

### Purpose
Create a web-based Checkers game using Maven and Freemarker, implementing
the game through frontend and backend development. Each player should be able to 
successfully play a game of checkers.


### Glossary and Acronyms
> _Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| VO | Value Object |


## Requirements

This section describes the features of the application.

### Definition of MVP
A user should be able to sign in to start playing checkers. A user once signed in
can start a game with another player. Each player should be able to play a standard game of
checkers and have the option to resign during the game.

### MVP Features
> _Provide a list of top-level Epics and/or Stories of the MVP._

* As a user I want to be able to sign in with the desired username of my choice.
* As a user I want to be able to start a game and play with another.
* As a user I want to be able to move a piece when I drag a piece to a valid square.
* As a user I want to be able to capture a piece when I drag a piece over an opponent's piece.
* As a user I want to be able to forfeit the game when I feel helpless to end the game.

### Roadmap of Enhancements
> _Provide a list of top-level features in the order you plan to consider them._


## Application Domain

This section describes the application domain.

![The WebCheckers Domain Model](domain-model.png)

A Player will play a Checkers game, making the move using the pieces. The Game
has a Board for the game to be played on. The Board is made up of Rows and Spaces, which
the pieces will be stored on.

## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

As a web application, the user interacts with the system using a
browser.  The client-side of the UI is composed of HTML pages with
some minimal CSS for styling the page.  There is also some JavaScript
that has been provided to the team by the architect.

The server-side tiers include the UI Tier that is composed of UI Controllers and Views.
Controllers are built using the Spark framework and View are built using the FreeMarker framework.  The Application and Model tiers are built using plain-old Java objects (POJOs).

Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the WebCheckers application.

![The WebCheckers Web Interface Statechart](web-interface.png)

The user will go to the home page when first coming to the application. The user will
then be able to get to the /signin page. If the name entered is valid, then they will be
taken to the /home page. If the name entered is invalid, then they will stay at the /signin
page. When the user is signed in and at the home page, the user will then be able to get to the
/game page to play a game. `GET /GameRoute` is called to begin the game. Drag and drop mechanics are used to move
the checkers from square to square. Buttons are used to submit the turn, undo the move, resign the game, and receive
a hint. The page will refresh periodically to update the board once the opponent submits their turn or resigns the
game.


### UI Tier
> _Provide a summary of the Server-side UI tier of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class structure or object diagrams) with some
> details such as critical attributes and methods._

> _You must also provide any dynamic models, such as statechart and
> sequence diagrams, as is relevant to a particular aspect of the design
> that you are describing.  For example, in WebCheckers you might create
> a sequence diagram of the `POST /validateMove` HTTP request processing
> or you might show a statechart diagram if the Game component uses a
> state machine to manage the game._

> _If a dynamic model, such as a statechart describes a feature that is
> not mostly in this tier and cuts across multiple tiers, you can
> consider placing the narrative description of that feature in a
> separate section for describing significant features. Place this after
> you describe the design of the three tiers._

The UI is composed of the routes needed to properly navigate the website and
play the game. All routes created inherit from the `Route` interface.
The user starts at the home route, then `GET /signin` is called to allow
the user to log in. After a successful log-in, the user is taken back to the homepage, 
where `GET /GameRoute` is called when a Player clicks on another Player to begin the game.
When a move is made, `POST /ValidateMove` is called which will verify the move is legal.
If the move is legal and the submit button is pressed, then `POST /SubmitTurn` is called,
which will update the board and allow the opponent to move. This process repeats until the
game is over.
![Sequence Diagram for PostValidateMoveRoute](validateMoveSD.png)


### Application Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

When a user goes to sign in, their name is put in the `PlayerLobby` to be stored. If
the name entered is already in the lobby, then the name is invalid and the user will have to
use a different name to sign in. When a game is started, the `GameManager` will put the players 
in a game and create the board.


### Model Tier
> _Provide a summary of the Model tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

When a user successfully signs in, they now become a `Player` in the lobby. When 
the players start a game, the `Boardview` represents the board, which consists of `Row`s
and `Space`s, which the `Piece`s are then placed on. A `GameModel` is also used to represent one game.
When a move is made on the board, a `Move` is created, which contains the start and end `Position` of 
the piece moved. The `ValidateMove` class is then used to verify whether the move was valid.
![UML Diagram for the checkers board](boardUML.png)



### Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements. After completion of the Code metrics exercise, you
> will also discuss the resutling metric measurements.  Indicate the
> hot spots the metrics identified in your code base, and your
> suggested design improvements to address those hot spots._

While Object-Oriented principles are followed, the use and consistency of them
are not apparent throughout the architecture. The Law of Demeter, in particular, is
a principle that could be improved throughout the design. In addition, there are some
classes that have been replaced with new classes, but have not been removed from the architecture.
This causes issues with coupling and can be fixed by replacing the obsolete classes with their successor
in the design, as well as implementing more abstraction so the issue can be avoided in the future.




## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

`PlayerLoby`, `ValidateMove`, `GetSignInRoute`, `PostSignInRoute`,
`GetHomeRoute`, `GameModel`, and a few others were tested. The tests could
have been more through with more testing.


### Acceptance Testing
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

No stories have passed all their tests, 
three stories have some acceptance criteria passed, and four have none passed.


### Unit Testing and Code Coverage
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._

More unit tests have to be done to get a more complete coverage at this time.
