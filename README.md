# Cloud_Computing_INFSCI_2750

Objective:
The objective of this mini project is to get familiar with setting up the Hadoop system and to
start programming in Hadoop. 

Part 1: Setting up Hadoop in Docker: 
This part requires you to build container from Docker (https://www.docker.com/) images,
which provide the environment you need for part 2 and 3.

Part 2: Developing a Hadoop program
n Part 2 of the project, you are required to develop a Hadoop program that produces the n-
gram frequencies of the text in a given input file. n-gram is a contiguous sequence of n
characters from a given sequence of text.
An n-gram of size 1 is referred to as a "unigram"; size 2 is a "bigram" (or, less commonly, a
"digram"); size 3 is a "trigram". For example, the 2-gram frequency in the text, “Helloworld”
is as follows:
He-1, el-1, ll-1, lo-1, ow-1, wo-1, or-1, rl-1, ld-1
Your program should accept n as an input parameter and produce the n-gram frequencies in
the text as an output file.

Part 3: Developing a Hadoop program to analyze real logs
n this part you need to develop MapReduce programs to analyze a real anonymous logs to
answer several questions based on the log. The log is in access_log.
The log file is in Common Log Format:
10.223.157.186 - - [15/Jul/2009:15:50:35 -0700] "GET /assets/js/lowpro.js HTTP/1.1"
200 10469
%h %l %u %t \"%r\" %>s %b
Where:
• %h is the IP address of the client
• %l is identity of the client, or "-" if it's unavailable
• %u is username of the client, or "-" if it's unavailable
• %t is the time that the server finished processing the request. The format is
[day/month/year:hour:minute:second zone]
• %r is the request line from the client is given (in double quotes). It contains the method, path,
query-string, and protocol or the request.
• %>s is the status code that the server sends back to the client. You will see see mostly status
codes 200 (OK - The request has succeeded), 304 (Not Modified) and 404 (Not Found). See
more information on status codes in W3C.org
• %b is the size of the object returned to the client, in bytes. It will be "-" in case of status code
304.
Problems:
1. How many hits were made to the website item “/assets/img/home-logo.png”?
2. How many hits were made from the IP: 10.153.239.5
3. Which path in the website has been hit most? How many hits were made to
the path?
4. Which IP accesses the website most? How many accesses were made by it?
For each question, you can write one or two MapReduce programs to get the answer. You
can use parts of the code from the first two questions (question 1 and 2) to build the programs
for questions 3 and 4.
