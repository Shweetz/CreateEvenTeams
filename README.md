# Create Even Teams

This program builds balanced teams from people with individual strength.

inputMain.txt is the file where you write each player's strength and name, customize it to use with you own data.

=> One line per player like this "strength name".

If the strength is not a value but a tier of skill, enter the tier as strength and customize map.txt, which does the mapping between each strength and its actual value.

=> One line per mapping like this "tier value".

## Current state
Every team is composed of 2 players, changing it requires changing the code. 
I may change this later, maybe add the team size on the first or last line of the input file.

The program can do weird things if the number of players is not a multiple of the number of teams, careful.

## How to use
TODO upload project

Download project and run the .jar file.
 
## How to help
git clone project

Put the src into an Eclipse project to compile

## Architecture
One main class Former.

One test class FormerTest.

