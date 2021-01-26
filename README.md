# mobilitymodels

To use our proposed mobility models, a user may download the *mobility_models.jar* file, and include it as a library in the user's Java project. 

A simple usage example is:

```java 
public static void main(String[] args) {
	Property property = new TCR_swarm();
	Constraint constraint = new Racetrack();
	Class node = FloodNode.class;
	new MobilityModel(property, constraint, node);
}
```

after which a JViewer window pops up, in which the user may start the mobility model throught the right-click menu.
