 
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/6cf5d40f-58af-43e4-a6a6-92aca8b182c8)

Victory Transport

•	Ömer Can Şimşek // Fri3m
•	Ömer Yıldırım // OmerrYildirim
•	Batuhan Kurt // batukurt1203


CSE1242 Computer Programming II, Spring 2023

Date Submitted: May 26, 2023
Problem Definition
Firstly, this game is a travel simulation. In this simulation, the player tries to take the passengers in the cities to the places they want to go via the shortest route. The player earns points according to the length of the routes he/she chooses and the number of passengers in the car. In addition, in order for the player to move to the new level, the passengers must leave all of them where they want to go, otherwise they cannot move to the new level. There is also a save/load system in the game for the user to save where he/she left off, so that the user can continue from the level he left at any time.
In summary, the aim of this game is to take the passengers in the cities to their desired destinations with the shortest route.
Implementation Details

![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/1985fd8e-0e74-4463-ac7d-e0a4d5399ac5)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/bcb89463-140a-49f9-9729-8957d6156321)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/eb7cc0b9-78a0-4691-8b4b-1c27c217ea17)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/197a7b4e-dd0f-4b36-9d34-a321cc15250d)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/b72079d9-839b-4279-ad52-0784aa9226dd)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/4e83e0f5-18cf-44a2-8de6-0fadec48a022)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/cab21462-c848-41d7-8e07-3d7f48be6085)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/694ad20b-cbf5-4e80-826a-cc443101b51f)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/1f506b3f-615f-4346-ab69-5772196a6ae8)
![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/a42c5c9f-1c52-4f89-aa82-006fa390d737)


We built the basis of this project on the BorderPane panel, but we also used different panels for some parts. Before the game starts, a menu opens and in this menu, the user can open a new game, open the saved section or exit the game. Then levels start.
Right Pane
At the top of the right panel of the project, a menu button welcomes the player and when he/she clicks this button, the next level, save and load buttons come. By using these buttons, the player can switch to the new level if he fulfills the condition, save the level he/she passed and continue from the recorded level. Under the menu button, the score collected by the player, the information of the passengers in the selected city and the information of the passengers in the current location are displayed. In the divided area below this section, the last actions performed are displayed. At the bottom of the right side, the information of the passengers in the vehicle and the number of empty / occupied seats in the vehicle are displayed. After these areas on the right are finished, there is a drive button in the lower right corner and the player completes the action by clicking this button after selecting the city.
Left Pane
	The left panel of the project is the main panel where the game is played. In this part, there are cities in the form of images and when these images are clicked, a path is drawn from the current location to the image that the player clicked. After selecting the image, the player starts the game by clicking the drive button and the vehicle starts to transport the passengers to the city chosen by the player. On the left panel, there are forbidden areas outside the cities and no roads pass through them. Roads are shaped and created according to these obstacles.

The parts we had difficulty with in the project:
•	Drawing the roads
•	Vehicle animation

We had a lot of trouble drawing the roads and getting them to the right location by bypassing the blocked and full cells.
We also encountered many problems in playing the vehicle animation on the roads we drew.

	
Additional features we added to the project are as follows:
1.	Main menu
2.	Save/load 
3.	Next level
4.	Clicked city information
5.	Current city information
6.	Last action information
7.	Vehicle information
8.	Finish Message

Test Cases

 ![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/67b67cac-2bbb-4058-9fc6-30735e8f82f6)
   In this screenshot,there is a main menu of our game which named ‘Victory Transport’.   There are 3 buttons which are ‘New Game’ , ‘Load Game’ and ‘Exit’ buttons.When we clicked on ‘New Game’ button, first level will be open.‘Load Game’ button is letting you to play wherever you stayed last time.‘Exit’ button is causing shut the program down. While the user is in the main menu;three type vehicle will move indefinitely.These are the types of vehicle which user will use in the levels.

 ![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/22228431-0f19-48f6-862a-9d3d2d9cda3f)
In this screenshot,bus is going over lines.

 ![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/7e59133b-47a4-466a-81ee-d48acef8f9b3)
In this screenshot,it is seen that user can load with clicking ‘Load’ button whatever he/she did.Thanks to this button,user will continue wherever he/she stayed.

 ![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/be32e399-5431-4c09-ae07-71aebe6befd6)

In this screenshot,it is seen that ‘Next Level’ button is available after all passenger went where they want to go.
 
 ![image](https://github.com/Fri3m/VictoryTransport/assets/108125233/cc784d08-121b-42d0-bacd-910c7331ff32)
When all passengers went where they want to go;this scene will be seen.





