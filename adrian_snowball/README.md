##Adrian's Draw Solution
***

I used Spring initializr to generate the project so that it can be easily packaged
up as an executable jar file. To run the app use:

`mvn spring-boot:run`

I just used an InputStreamReader to create the interactive terminal, the only issue
I found with this approach was the lack of an elegant way to test the input on the terminal.

###Assumptions

* If another create canvas command is received after the initial one we throw an exception.
* You cannot draw a line that exceeds the canvas space.

###Design Approach

I chose not to use any third party libraries beyond Spring Boot as I didn't see the need 
for it. The domain model as far as I can tell is just a canvas and lines, a rectangle simply 
comprises four lines. A line is made up of a list of points. This was the simplest minimal 
model I could come up with. I modelled the canvas as a singleton as there should only ever
be one instance of it. Line and it's points are represented as data classes as they only
have state. I'd like to think my solution is easy to read and extensible as any future shapes
could be simply modelled as a list of points and handed to the LineRenderer to output.