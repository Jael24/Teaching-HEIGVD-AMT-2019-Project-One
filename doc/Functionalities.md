# Functionalities
## Data Model
The data model is a simple N to N relation. It uses `Actor` as the first entity. An `Actor` can play as a `Character` in a `Movie`. 
An `Actor` has the following attributes:
- A name
- An ID
- A password

A `Character` has the following attributes:
- A character name
- An ID
- An actor and a film

A `Movie` has the following attributes:
- A title
- An ID

## Functions available to the user
- When you are connected, several functionalities are available:
 - The user can see his informations, can edit his name and password, or even delete his account.
 - The user can see, edit and delete every film in which he played.
 - The user can see, edit and delete every character that he played.
 - The user can create a new film or a new character.
