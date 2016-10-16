My assignement is implemented in Java.

How to run:
1.Type 'make' to compile client.java and server.java
2.Run server to get the n_port
  ./server.sh <req_code>
3.Run client using the n_port which server give you on another machine
  ./client.sh <server address> <n_port> <req_code> <msg>
4.You should see the reversed sentence in client's display
5.Type 'make clean' to delete client.class and server.class

Version:
javac -version: javac 1.8.0_91
make -v: GNU Make 3.81
hostname -f: ubuntu1204-004.student.cs.uwaterloo.ca
