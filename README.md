README.md<h1 align="center"> CPU Scheduler 📅 with Gantt Chart 📊 </h1>

<h2 style="font-weight:bold">✍ Authors</h2>
<ul>
<li>John Gabriel Buenaventura</li>
<li>Jose Mari Tan</li>
<li>Maria Clarissa Marasigan</li>
<li>Carl Nicolas V. Mendoza</li>
</ul>

<h2 style="font-weight:bold">✅ Current Features as of 2023-02-24T2107H ☑</h2>
<b>Process.java ⚒</b>
<ul>
<li>Constructor: burst time, arrival time, and process id</li>
<li>timeRandomizer(): randomizes arrival time and burst time</li>
<li>getPid()</li>
<li>getAt()</li>
</ul>
<b>Schedule.java 📅</b>
<ul>
<li>Constructor: contains nothing</li>
<li>createProcess(int id): creates Process object and returns it</li>
<li>createSchedule(): choose number of processes, choose what type of CPU scheduling</li>
<li>fcfs(Process[] p): accepts process array, only adds to the queue (unfinished)</li>
<li>calculateWaitingTime(Process p, int ft): calculates waiting time</li>
<li>run(): for threading, just in case it's needed</li>
</ul>

<h2 style="font-weight:bold">What work do you want to do?</h2>
<ul>
<li>First Come First Serve (FCFS)</li>
<li>Shortest-Job-First (SJF) Scheduling</li>
<li>Shortest Remaining Time</li>
<li>Priority Scheduling</li>
<li>Round-Robin Scheduling</li>
<li>Multilevel Queue Scheduling</li>
<li>GUI (unnecessary)</li>
<li>Gantt Chart</li>
<li>Documentation Format</li>
</ul>

<h2 style="font-weight:bold">Notes</h2>

<p>

<b>First-Come, First-Served (FCFS) Scheduling:</b>

<ul>
  <li>Waiting time = 0 for the first process, and for subsequent processes, it is the sum of the burst times of all previous processes</li>

  <li>Turnaround time = Burst time + Waiting time</li>
</ul>

<b>Shortest-Job-First (SJF) Scheduling:</b>

<ul>
  <li>Waiting time = Time process is ready to execute - Arrival time</li>

  <li>Turnaround time = Burst time + Waiting time</li>
</ul>

<b>Priority Scheduling:</b>

<ul>
  <li>Waiting time = Time spent in the ready queue waiting for CPU time</li>

  <li>Turnaround time = Burst time + Waiting time</li>
</ul>

<b>Round-Robin Scheduling:</b>

<ul>
  <li>Waiting time = Turnaround time - Burst time</li>

  <li>Turnaround time = Time at which the process completes - Arrival time</li>
</ul>

<b>Multilevel Queue Scheduling:</b>

In Multilevel Queue Scheduling, processes are divided into different queues based on their characteristics (e.g., priority, memory requirements, etc.), and each queue is assigned a different scheduling algorithm. The waiting time and turnaround time formulas for Multilevel Queue Scheduling depend on the scheduling algorithms used for each queue.

Assuming each queue uses the FCFS scheduling algorithm, the formulas are:

<ul>
<li>Waiting time = 0 for the first process in each queue, and for subsequent processes in each queue, it is the sum of the burst times of all previous processes in that queue</li>

  <li>Turnaround time = Burst time + Waiting time</li>
</ul>

For the case where each queue uses the Round Robin scheduling algorithm, the formulas are:

<ul>
  <li>Waiting time = Turnaround time - Burst time</li>

  <li>Turnaround time = Time at which the process completes - Arrival time</li>
</ul>

<b>Shortest Remaining Time (SRT) Scheduling:</b>

<ul>
  <li>Waiting time = Turnaround time - Burst time</li>

  <li>Turnaround time = Time at which the process completes - Arrival time</li>
</ul>
</p>

<h3 align="left">⌨ Language Used</h3>
<p align="left">
<a href="https://www.java.com/en/"><img width="75px" src="https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg" alt="Java Icon" /></a>
</p>

<h2 style="font-weight:bold;">TASK 💽</h2> 
In a group (maximum of 4 members per group), create a program that will simliate and show the Gantt chart 📊 of each CPU schedliing Algorithm.

<h3 style="font-weight:bold;">Notes 📝</h3>
<ul>
<li>
Choose your own programming language ✔
</li>
<li>
One program, one CPU scheduling Algorithm or one program, six CPU scheduling Algorithms (Use selection to choose what CPU scheduling Algorithm to show) ✔
</li>
<li>
Also, display/ print the waiting time and turnaround time for each process
</li>
<li>
Display Average Waiting time
</li>
<li>
Display Average Turnaround Time
</li>
<li>
Process may all arrive at time 0 or may arrive any time
</li>
<li>
Each process has its burst time. ✔
</li>
<li>
Priority might be observed in some cases
</li>
<li>
Time Slice might be observed in some cases
</li>
<li>
Pre-emptive or Non pre-emptive might be observed in some cases
</li>
<li>
Create a documentation (use your own format) then upload it as your answer
</li>
<li>
Documentation should contain:
</li>
  <ul>
  <li>
  Members of the group with corresponding role/contribution in the development of the activity
  </li>
  <li>
  Short description about each CPU schedliing Algorithm
  </li>
  <li>
  Short description about the program
  </li>
  <li>
  Detailed explanation of each CPU schedliing Algorithm based on the created program
  </li>
  <li>
  Programming Codes
  </li>
  <li>
  Sample and explanation for each CPU schedliing Algorithm while running the created program
  </li>
  </ul>
<li>
Group leader will send the documentation on behalf of the whole group
</li>
<li>
You may add other features to make the program better
</li>
<li>
Record the program presentation of your output
</li>
<li>
The group may use any video recording tool to demo the running program. All members of the group sholid participate on the recorded video demo of the running program
</li>
</ul>