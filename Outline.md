#Outline
Designing and implementing a GamePanel and GameEntity


GamePanel
  // Mouse Fields
  int mouseX; // last recorded posistion of the mouse's X value
  int mouseY; // last recorded posistion of the mouse's Y value
  int mouseButton; // last pressed button of the mouse
  
  // Key Fields
  ArrayList<KeyEvent> keysDown; // an Arraylist containing all of the currently held down keys
  
GameEntity
  // Generic Fields
  GamePanel game; // refrence to the current game
  String name; // name of the entity, usefull for defining refrences without a variable

  // Specific fields
  boolean selected; // true if the Entity is selected by the mouse
  int xPos; // current X posistion of the entity
  int yPos; // current Y posistion of the entity
  
  abstract void onClickUpdate(); // called when the mouse is clicked anywhere on the screen
  
  abstract void passiveUpdate(Graphics2D); // contains any updates that don't require an event
