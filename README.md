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