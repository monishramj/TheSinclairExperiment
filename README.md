# The Sinclair Experiment

A text-based interactive horror adventure game where you play as an exorcist investigating supernatural occurrences on the mysterious island of Kinmouth, England. Created by **Monish RJ** & **Vighnesh S**.

## Story
You are a renowned exorcist sent by the Vatican to investigate a series of disturbing events on the remote island of Kinmouth. Locals report hearing eerie weeping at night, and young women have been disappearing like clockwork every two weeks. As the last exorcist of the Vatican, you must uncover the truth behind the hauntings and save the island from the evil that plauges it.

## Gameplay Features

### Core Mechanics
- **Text-Based Navigation**: Explore various locations using cardinal directions (NORTH, SOUTH, EAST, WEST)
- **Dialogue System**: Interact with NPCs through branching dialogue trees
- **Inventory Management**: Collect items and weapons throughout your journey
- **Combat System**: Engage in turn-based battles with supernatural entities
- **Multiple Endings**: Your choices determine the fate of Kinmouth, with 6 different endings

### Combat System
- **Turn-based battles** with health and energy management
- **Normal attacks** and **Special attacks** unique to each weapon
- **Critical hits** for 2x damage
- **Energy system** that charges special attacks
- **Strategic fleeing** option (50% success rate)

### Key Features
- **Dynamic NPC interactions** with memory of player choices
- **Cipher puzzles** to unlock secrets
- **Mini-game chase sequence** (GUI-based endless runner)
- **Item collection** and weapon upgrades
- **Multiple investigation paths** leading to different outcomes

## Technical Details

### Prerequisites
- Java JDK 8 or higher
- Swing library (included in JDK)

### Key Classes
- **Main.java** - Entry point and game initialization
- **Player.java** - Player stats, inventory, and equipment
- **Battle.java** - Turn-based combat system
- **DialogueTree.java** - Branching conversation system
- **Room.java** - Location data structure
- **ForestChaseGame.java** - Interactive chase sequence

## How to Play

### Starting the Game
1. Compile and run `Main.java`
2. Enter your character's last name
3. Follow the on-screen prompts

### Basic Commands
```
GO [DIRECTION]       - Move in cardinal directions (NORTH/SOUTH/EAST/WEST)
EXPLORE             - Scan your surroundings
TALK                - Interact with NPCs
LOOK                - Examine your current location
TAKE [ITEM]         - Pick up items (use ALL CAPS name)
USE [OBJECT]        - Interact with objects
INVENTORY/INVEN     - Check your items
LEAVE               - Exit current area
HELP                - Display available commands
```

### Combat Commands
```
0 - INFO           - Display combat help
1 - Normal Attack  - Standard attack
2 - Special Attack - Powerful attack (requires energy)
3 - Skip Turn      - Charge energy
4 - Flee           - Attempt to escape (50% chance)
```

### Tips for Success
- **Talk to everyone** - NPCs hold crucial information
- **Explore thoroughly** - Items are hidden in specific locations
- **Time matters** - Some choices are time-sensitive
- **Read carefully** - Dialogue contains important clues
- **Investigate systematically** - Follow logical investigation paths
- **Save points exist** - You can retry from key moments

## Special Features

### ASCII Art
- Atmospheric title screens
- Location-specific artwork
- Ghost and monster visualizations
- Dynamic loading animations

### Dialogue System
- Over 100 unique dialogue nodes
- Character-specific conversation trees
- Choice-driven narrative branching
- NPC memory system


## Known Issues

- Some dialogue paths may lead to dead ends (intentional design)
- The forest chase game requires good timing
- Certain items must be obtained in specific order
---

**Note:** This is a horror game with dark themes. Player discretion is advised.

*"Is ignorance truly bliss?"*
