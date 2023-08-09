# Propositional Logic Tool

## Introduction

Propositional Logic Tool is a simple GUI tool for the comparison
of propositional logic statements, conversion of statements to and
from truth tables, and validity checking of logical arguments.

This app is useful for people working with propositional logic statements,
be it for school, work, or just out of curiosity and self-learning.

When I took CPSC 121, I frequently found myself searching for tools
online to check my work involving propositional logic, and I often 
found that they were cumbersome to use, having to keep multiple tabs 
open to check back and forth, as well as having trouble reading what
I put down. I hope that this project will provide a comprehensive 
all-in-one tool for anyone working with zero-th order propositional logic.

## User Stories

- As a user, I can input a propositional logic statement and receive its
equivalent truth table
- _As a user, I can freely edit the outcomes of a truth table and receive 
its equivalent propositional logic statement_ **WIP**
- As a user, I can enter two distinct propositional logic statements and 
see if they are equivalent
- _As a user, I can input a logical argument and receive either the order
of operations showing it to be valid, or an invalidating truth assignment_ **WIP**
- _As a user, I can see my input's syntax being highlighted_ **WIP**
- As a user, I can see a history of prior queries _not implemented in the GUI_
- As a user, I can add and remove selected queries to a canvas of queries
- As a user, I can freely move queries on the canvas
- _As a user, I can edit queries from the canvas_ **WIP**
- As a user, I can save the current canvas for use after app restart
- As a user, I can load a canvas of my choosing from a save file

## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by clicking "Save to canvas" 
after doing a query
- You can generate the second required action related to adding Xs to a Y by clicking "Remove Query"
under a query in the canvas view
- You can generate a third action related to adding Xs to a Y by dragging queries on the canvas to rearrange them
- You can locate my visual component by starting the program. A splash screen will appear before the 
main menu is reached
- You can save the state of my application by navigating to File -> Save Canvas or pressing Ctrl+S
- You can reload the state of my application by navigating to File -> Load Canvas or pressing Ctrl+L

## Phase 4: Task 2
Sample log based on the following actions:\
_Note: Many log entries of query positions being updated have been moved to appendices for readability_
1. Load Canvas from save file
2. Add 3 queries and move them
3. Save Canvas
4. Exit

* Wed Aug 09 03:02:04 PDT 2023
* Truth table conversion: "a ^ b" created.
* Wed Aug 09 03:02:04 PDT 2023
* Truth table conversion: "a ^ b" added to Canvas for ui testing.
* Wed Aug 09 03:02:04 PDT 2023
* Comparison: "a xor b" vs "(a -> b) v (a <-> b)" created.
* Wed Aug 09 03:02:04 PDT 2023
* Comparison: "a xor b" vs "(a -> b) v (a <-> b)" added to Canvas for ui testing.
* Wed Aug 09 03:02:04 PDT 2023
* Loaded Canvas for ui testing with 2 queries from: ./data/testCanvas.json
* Wed Aug 09 03:02:29 PDT 2023
* Truth table conversion: "a v b" created.
* Wed Aug 09 03:02:33 PDT 2023
* Truth table conversion: "a v b" added to Canvas for ui testing.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 0.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 1.
* _[\<\<appendix A\>\>](#appendix-a)_
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 7.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:03:05 PDT 2023
* Comparison: "a v b" vs "a xor b" created.
* Wed Aug 09 03:03:08 PDT 2023
* Comparison: "a v b" vs "a xor b" added to Canvas for ui testing.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 3.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 4.
* [_\<\<appendix B\>\>_](#appendix-b)
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 357.
* Wed Aug 09 03:04:15 PDT 2023
* Comparison: "p v (q ^ r)" vs "(p v q) ^ (p v r)" created.
* Wed Aug 09 03:04:17 PDT 2023
* Comparison: "p v (q ^ r)" vs "(p v q) ^ (p v r)" added to Canvas for ui testing.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 4.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* [_\<\<appendix C\>\>_](#appendix-c)
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 638.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 97.
* Wed Aug 09 03:04:25 PDT 2023
* Comparison: "a xor b" vs "(a -> b) v (a <-> b)" removed from Canvas for ui testing.
* Wed Aug 09 03:04:26 PDT 2023
* Truth table conversion: "a ^ b" removed from Canvas for ui testing.
* Wed Aug 09 03:04:30 PDT 2023
* Saved Canvas for ui testing with 3 queries to: ./data/testCanvas.json



## Phase 4: Task 3
The first thing that comes to mind is refactoring TruthTableUI to 
use the singleton pattern, since right now, each query frame has its own 
printer, on top of the main window having its own printer. Since TruthTableUI 
only has a single public method that takes a TruthTable and creates a JScrollPane
out of this, having multiple instances is redundant and takes up unnecessary 
resources. This would also  simplify the UML Class diagram, since LogicToolUI 
and QueryUI wouldn't need their own instances of TruthTableUI anymore.

Another thing I could change is to have CanvasUI and QueryUI mirror the relationship
between Canvas and Query. Currently, CanvasUI is very empty, providing just a blank
desktop window to add Query frames onto, with just one method relating to the model
that initializes the queries in its Canvas. QueryUI carries all the responsibility of
tracking its movement, updating the position in Canvas, and removing its corresponding
Query from the Canvas. I believe this violates the Single Responsibility Principle.
If instead CanvasUI had a list of QueryUI, perhaps I could eliminate the association 
between QueryUI and Canvas. Then, CanvasUI would be responsible for listening for 
movement of its internal frames, updating the position in Canvas when it happens, and
removing queries from the Canvas when an internal frame is removed.

## Appendices
### Appendix A
[jump to sample log](#phase-4-task-2) 
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -1.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 1.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -1.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 2.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -1.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 3.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -1.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 4.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -2.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 5.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -2.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 6.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -3.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 8.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -4.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 9.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -4.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 11.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -5.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 13.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -5.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 14.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -6.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 17.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -7.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 18.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -8.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 20.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -8.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 21.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -9.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 24.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -9.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 25.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -10.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 28.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -12.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 30.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -13.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 33.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -13.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 34.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -14.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 37.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -15.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 40.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -16.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 42.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -17.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 45.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -18.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 47.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -19.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 50.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -20.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 53.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -21.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 56.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -22.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 58.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -23.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 61.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -24.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 64.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -25.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 67.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -25.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 70.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -27.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 73.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -28.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 76.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -29.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 79.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -29.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 82.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -30.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 85.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -31.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 89.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -32.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 92.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -33.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 94.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -34.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 97.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -35.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 101.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -36.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 104.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -37.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 107.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -37.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 109.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -38.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 113.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -38.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 116.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -39.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 119.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -40.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 122.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -41.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 125.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -41.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 128.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -42.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 131.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -42.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 133.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -43.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 137.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -44.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 139.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -45.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 142.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -45.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 145.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -45.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 148.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -46.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 150.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -46.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 153.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -47.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 156.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -47.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 158.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -48.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 161.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -48.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 164.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -49.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 166.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -49.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 169.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -49.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 171.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -49.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 173.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -50.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 176.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -50.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 178.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -50.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 181.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -51.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 183.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -51.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 185.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -51.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 187.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -52.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 189.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -52.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 192.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -52.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 193.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -52.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 196.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -52.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 197.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 200.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 201.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 204.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 205.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 207.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 209.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 211.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 213.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 214.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 217.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 219.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 221.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 222.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 224.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 225.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 227.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 229.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 230.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 232.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -55.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 233.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -55.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 235.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -55.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 237.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -55.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 239.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -55.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 241.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -55.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 242.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 243.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 245.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 246.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 247.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 248.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 249.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 250.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 251.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 252.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 253.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 254.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -56.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 255.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -55.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 256.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -54.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 257.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 257.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -53.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 258.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -52.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 259.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -51.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 259.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -50.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 260.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -49.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 261.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -48.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 261.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -47.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 261.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -46.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 262.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -45.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 262.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -44.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 263.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -43.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 263.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -42.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 264.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -41.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 264.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -41.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 265.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -40.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 265.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -38.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 265.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -37.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 265.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -37.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 266.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -35.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 266.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -34.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 266.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -33.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 267.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -31.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 268.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -29.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 269.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -28.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 269.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -27.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 269.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -25.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 269.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -25.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 270.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -23.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 271.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -22.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 271.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -21.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 271.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -21.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 272.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -20.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 272.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -18.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 273.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -17.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 273.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -15.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 273.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -13.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 274.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -12.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 275.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -10.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 275.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -10.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 276.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -9.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 276.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -8.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 276.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -7.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 277.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -6.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 277.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -5.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 277.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -4.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 277.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -3.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 277.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -2.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 277.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -1.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 277.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to -1.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 278.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 0.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 278.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 1.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 278.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 2.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 279.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 3.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 279.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 4.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 279.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 5.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 280.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 6.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 280.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 7.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 280.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 8.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 9.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 10.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 11.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 12.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 13.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 14.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 15.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 16.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 282.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 17.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 282.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 18.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 282.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 19.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 282.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 20.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 21.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 22.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 23.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 24.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 25.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 26.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 284.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 27.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 284.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 26.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 284.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 25.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 284.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 24.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 284.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 23.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 22.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 283.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 21.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 282.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 20.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 282.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 19.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 282.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 19.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 18.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 17.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 16.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 15.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 14.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 13.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 12.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 11.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 10.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 9.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.
* Wed Aug 09 03:02:40 PDT 2023
* X position of "Truth Table Conversion" updated to 8.
* Wed Aug 09 03:02:40 PDT 2023
* Y position of "Truth Table Conversion" updated to 281.\
[jump to sample log](#phase-4-task-2)

### Appendix B
[jump to sample log](#phase-4-task-2)
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 4.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 6.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 5.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 7.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 5.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 8.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 7.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 10.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 8.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 12.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 9.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 14.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 11.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 16.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 13.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 20.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 14.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 22.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 17.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 25.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 18.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 29.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 21.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 32.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 23.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 36.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 26.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 41.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 29.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 46.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 32.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 51.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 36.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 56.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 39.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 61.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 43.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 68.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 47.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 73.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 51.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 80.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 55.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 86.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 60.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 92.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 65.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 100.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 69.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 107.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 74.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 114.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 79.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 121.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 84.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 129.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 89.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 136.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 94.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 144.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 99.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 152.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 105.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 160.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 110.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 168.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 116.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 176.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 121.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 185.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 126.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 193.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 132.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 201.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 137.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 209.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 143.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 217.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 149.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 225.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 154.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 233.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 159.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 241.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 165.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 249.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 170.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 256.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 176.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 264.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 181.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 272.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 186.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 279.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 192.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 287.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 197.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 293.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 202.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 300.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 207.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 308.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 213.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 314.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 217.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 320.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 222.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 328.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 227.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 333.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 232.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 340.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 237.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 346.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 241.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 352.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 245.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 357.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 250.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 363.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 254.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 368.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 258.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 373.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 262.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 378.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 266.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 383.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 269.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 388.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 273.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 392.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 277.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 396.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 279.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 401.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 282.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 405.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 285.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 408.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 288.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 412.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 290.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 416.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 292.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 419.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 294.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 422.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 296.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 425.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 297.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 428.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 299.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 430.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 301.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 432.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 301.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 435.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 302.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 436.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 303.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 438.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 304.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 440.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 304.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 441.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 305.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 442.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 305.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 443.
* Wed Aug 09 03:03:12 PDT 2023
* X position of "Comparison" updated to 305.
* Wed Aug 09 03:03:12 PDT 2023
* Y position of "Comparison" updated to 444.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 301.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 441.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 300.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 440.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 300.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 439.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 299.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 438.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 298.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 438.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 297.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 437.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 297.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 436.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 295.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 435.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 294.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 434.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 293.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 433.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 292.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 431.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 291.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 430.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 290.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 429.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 289.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 428.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 288.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 426.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 286.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 426.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 285.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 424.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 284.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 422.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 282.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 422.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 281.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 420.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 280.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 418.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 279.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 418.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 277.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 416.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 276.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 414.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 275.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 413.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 273.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 412.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 273.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 410.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 272.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 410.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 270.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 408.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 269.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 406.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 268.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 404.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 266.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 403.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 265.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 402.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 265.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 401.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 264.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 399.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 263.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 398.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 262.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 397.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 261.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 396.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 261.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 394.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 260.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 393.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 259.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 391.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 258.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 390.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 257.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 389.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 257.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 388.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 257.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 386.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 256.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 385.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 255.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 384.
* Wed Aug 09 03:03:13 PDT 2023
* X position of "Comparison" updated to 255.
* Wed Aug 09 03:03:13 PDT 2023
* Y position of "Comparison" updated to 383.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 254.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 382.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 254.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 381.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 254.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 380.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 253.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 379.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 253.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 378.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 253.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 377.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 253.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 376.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 252.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 375.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 252.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 374.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 252.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 373.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 251.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 372.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 251.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 371.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 251.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 370.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 250.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 369.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 250.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 368.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 250.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 367.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 250.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 366.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 366.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 365.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 364.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 363.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 362.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 361.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 360.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 359.
* Wed Aug 09 03:03:14 PDT 2023
* X position of "Comparison" updated to 249.
* Wed Aug 09 03:03:14 PDT 2023
* Y position of "Comparison" updated to 358.\
[jump to sample log](#phase-4-task-2)

### Appendix C
[jump to sample log](#phase-4-task-2)
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 5.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 6.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 7.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 8.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 9.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 10.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 11.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 12.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 13.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 14.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 15.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 16.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 18.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 19.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 20.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 0.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 21.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 22.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 23.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 25.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 26.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 27.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 28.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 30.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 31.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 33.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 1.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 35.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 2.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 37.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 2.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 38.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 2.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 40.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 2.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 42.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 3.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 43.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 3.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 46.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 3.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 47.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 4.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 50.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 4.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 51.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 4.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 54.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 5.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 56.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 5.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 58.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 5.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 60.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 5.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 62.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 5.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 65.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 6.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 67.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 6.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 70.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 6.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 73.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 7.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 75.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 7.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 78.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 8.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 81.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 9.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 83.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 9.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 86.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 9.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 90.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 9.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 92.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 10.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 95.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 10.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 98.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 11.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 101.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 11.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 104.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 12.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 107.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 12.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 110.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 13.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 114.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 13.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 117.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 13.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 120.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 14.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 123.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 15.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 126.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 15.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 130.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 16.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 134.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 17.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 137.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 17.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 140.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 17.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 143.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 18.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 147.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 19.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 150.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 19.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 154.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 20.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 158.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 21.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 161.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 21.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 164.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 22.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 168.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 22.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 171.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 23.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 175.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 24.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 178.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 25.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 182.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 25.
* Wed Aug 09 03:04:22 PDT 2023
* X position of "Comparison" updated to 186.
* Wed Aug 09 03:04:22 PDT 2023
* Y position of "Comparison" updated to 26.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 190.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 27.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 193.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 27.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 197.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 28.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 200.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 29.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 204.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 29.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 208.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 30.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 211.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 31.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 215.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 32.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 219.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 33.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 222.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 33.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 226.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 34.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 230.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 35.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 234.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 36.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 238.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 37.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 242.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 37.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 246.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 38.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 250.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 38.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 254.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 39.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 257.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 40.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 261.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 41.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 265.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 41.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 269.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 42.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 273.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 43.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 276.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 44.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 280.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 45.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 284.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 45.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 287.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 46.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 291.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 46.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 295.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 47.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 298.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 48.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 302.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 49.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 306.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 49.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 310.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 50.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 314.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 50.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 318.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 51.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 321.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 52.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 324.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 53.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 328.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 53.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 331.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 53.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 335.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 54.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 338.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 55.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 342.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 56.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 346.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 56.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 349.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 57.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 352.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 57.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 356.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 58.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 359.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 58.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 362.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 59.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 366.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 60.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 370.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 61.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 373.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 61.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 376.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 61.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 379.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 62.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 383.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 63.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 386.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 63.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 390.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 64.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 393.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 65.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 396.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 65.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 399.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 65.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 402.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 66.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 406.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 66.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 409.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 67.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 412.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 68.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 415.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 68.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 418.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 69.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 422.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 69.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 425.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 69.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 427.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 70.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 430.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 70.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 433.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 71.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 436.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 71.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 438.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 72.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 442.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 72.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 444.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 73.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 447.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 73.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 450.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 73.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 452.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 73.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 454.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 74.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 457.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 74.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 460.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 75.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 462.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 75.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 465.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 76.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 467.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 76.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 470.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 76.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 472.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 77.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 474.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 77.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 477.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 77.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 479.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 77.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 482.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 78.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 484.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 78.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 486.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 78.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 489.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 79.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 491.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 79.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 494.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 80.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 495.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 80.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 498.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 81.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 500.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 81.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 502.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 81.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 505.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 81.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 507.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 81.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 510.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 82.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 511.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 82.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 514.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 82.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 516.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 82.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 518.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 83.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 520.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 83.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 522.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 83.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 525.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 84.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 526.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 84.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 529.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 84.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 531.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 84.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 533.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 535.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 537.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 539.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 541.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 543.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 545.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 546.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 85.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 548.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 86.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 550.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 86.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 552.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 86.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 554.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 86.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 555.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 87.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 557.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 87.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 558.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 87.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 560.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 87.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 562.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 87.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 563.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 88.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 566.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 88.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 567.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 88.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 569.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 88.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 571.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 573.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 574.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 576.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 578.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 580.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 582.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 583.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 89.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 585.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 90.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 586.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 90.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 589.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 90.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 591.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 91.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 593.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 91.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 594.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 91.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 596.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 92.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 598.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 92.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 600.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 92.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 602.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 603.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 605.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 607.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 609.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 610.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 612.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 614.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 93.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 615.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 94.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 617.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 94.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 618.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 94.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 620.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 94.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 622.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 95.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 623.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 95.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 625.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 95.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 626.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 95.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 627.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 95.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 629.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 96.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 630.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 96.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 631.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 96.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 633.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 96.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 634.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 96.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 635.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 96.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 636.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 97.
* Wed Aug 09 03:04:23 PDT 2023
* X position of "Comparison" updated to 637.
* Wed Aug 09 03:04:23 PDT 2023
* Y position of "Comparison" updated to 97.\
[jump to sample log](#phase-4-task-2)