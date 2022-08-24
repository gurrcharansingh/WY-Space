# WY-Space


Project Name- WYSpace

Technology used:- Java 7,8

Classes:-
1)Satellite:- It contains four fields corresponding to satellite pass parameters (name,bandwidth,startTime,endTime).
2)WyspaceMain:- Its the main execution class which takes user input for ground station bandwidth as an argument and then calculates and generates the required output based on the logic.

Text file:- 
1)pass-schedule.txt:- This text file is placed in src folder of the project and contains list of all the satellites schedules.

Basic logic behind the program:-
 1)First we count how many times a specific start time of each satellite pass is recurring.
 2)Then the start time of pass with max counts will be considered as time at which total downlink will be at its maximum.
 3) Next, total bandwidth of passes with max counts is checked against the provided ground station bandwidth.

As of now, my followed methodology to solve this exercise is:
1) Firstly, program asks user to provide custom inputs for ground station bandwidth and it must be a valid numeric number(validation logic added).
2) Then, text file containing satellites schedules is imported internally and parsed line by line.
3) List of satellites class object is created containing parsed parameters of satellites schedules.
4) Then we group by the start time of all passes and count exactly how many times each start time is recurring.
5) Next, we accumulate the start time of passes with max number of counts in the list.
7) Time at which total downlink will be at its maximum will be then generated out of the accumulated list.
8) Total bandwidth of passes with max counts contained in our List is calculated.
9) Next ,equivalency is checked whether user provided ground station bandwidth supports total bandwidth of satellite passes or not.
10) Finally, all our required results will be printed on console.


Steps to import the project zip file in eclipse or STS:-
1) In eclipse, Go to file> Import > Under General, Select Existing Projects into workspace> Click on next.
2) In the next dialog, select the archive file radiobutton and browse the directory where project zip file resides.
3) Select the zip file and open it.
4) Subsequently, it will automatically copy the projects into workspace. 
5) Click finish, and the projects get imported.


Steps to run the project in eclipse or STS:-
1)In order to run the project, right click on project >Then click on "Run As" >Java Application. and the program will successfully run.

Output console will give results as follows:-
 1)Please enter ground station bandwidth : // takes valid user input - (validation logic added).
 2)Time at which total downlink will be at its maximum is:
 3)Ground station provided bandwidth: //input bandwidth argument provided by user for ground station. 
 4)Total bandwidth required : 
 5)Ground station has the bandwidth to support this: NO/YES //determine if ground station has the bandwidth to support it
