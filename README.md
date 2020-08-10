# Vanim
### It's manim, but worse
A vector animation software that can be used for educational purposes and for vector art drawing. Uses the latest version of Processing (in the library imports).

![VANIM](/coverImages/vanim.gif)

## Installation
Vanim uses Gradle and the Gradle build system. 
It also uses [Processing](https://processingfoundation.org/) as the main framework
Other dependencies are: 
* [JLatexMath](https://github.com/opencollab/jlatexmath)
* [Apache Batik (SVG)](https://xmlgraphics.apache.org/batik/)
* [Apache FOP](https://xmlgraphics.apache.org/fop/)

## Running the program
Vanim can be run via the command line like so:
```sh
gradle run 
```
or via an IDE (like IntelliJ)

## Basic commands and explanation of the project
Vanim utilizes Processing's draw method to display something every frame. This has been further abstracted by creating scenes
where certain lines of code are run at a time (in batches) to avoid overbearing complexity.

Currently, there are very limited shapes that can be drawn, but over time the selection of polygons will be increased. 

## that's really about it, licensed under GPL 3 
