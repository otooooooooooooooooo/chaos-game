# fractals

Project presented at Kutaisi International University Visual Math Fest 2021
--
@credits: Otar Kalandadze

--brief overview--
Program is dedicated to simulate user|pre-defined repetitive operations arbitrarily many times.
Iterating over the same observable visual operations sometimes result in fractal shapes.
The goal was to create the tool for users (namely students, teachers or simply curious ones) to
experiment with maths and discover/generate fascinating images.
__________________

--------------------------------------------------------------explanation-----------------------------------------------------------
Two main types of simulation are presented:

Chaos Game:
The chaos game starts with two main components - current point on image and vertices.
Game has 4 steps:
1) A random vertex is chosen.
2) The current point interacts with the chosen vertex and new point is generated. 
3) New point becomes current.
4) step 1)

------------------------------------
Each vertex has its unique parameters:
 *compression ratio - the ratio of distance from vertex to current point and new point. (ex: 2.0 ratio creates new point which is twice as closer to vertex, it can hold any float
value.
 *rotation degree - the points rotate towards vertex. Rotation needs degree.
 *rotation direciton - surprisingly, rotation needs direction too (clockwise | counter-clockwise).
 *color - color of the generated point. It's all about visuals.
 *quantity - quantity can be thought as a coefficient of probability. If one vertex has quantity 1 and other has 2, there is twice as higher chance of chosing second vertex.

------------------------------------
 
Chaos Game has 2 modes:
Polygon mode - Arbitrarily many predefined vertices which form regular polygon. Colors of vertices are randomized. User can choose one of few predefined extra rules of
simulation.

Advanced mode - Nothing is predefined. User can create arbitrarily many vertices with arbitrary parameters. Time to get creative.


Affine Transformations:
Here aslmost everything is predefined and ready to simulate. But a single member of matrix can be chosen by you. Check and see what happens when you change its value slightly
up and down.

---------------------------------------------------------------------------------------------------------------------------------------------


-----controls-----
Mouse left-click - Initiate current point (used in Advanced Chaos Game mode)
Mouse Right-click - Place new vertex (used in Advanced Chaos Game mode)

spacebar - do one iteration (draw one more point)
enter - do 500 iterations
F - do 1 million iterations (might take time if rotation parameter is used in Advanced mode)
E - export image (in advanced mode, it exports the template which can be used again to import and simulate / moderate again)
esc - minimize window
R - restart program (available when the window is open)

-----------------
!!!Exporting project!!! -
FileAlreadyExists exception is not handled in program. So make sure not to use same project names in same directories.

!!!Importing template!!! -
To import template, select the template folder generated after exporting project (or download predefined template folders) when asked. Do not moderate the file names.

----------------
