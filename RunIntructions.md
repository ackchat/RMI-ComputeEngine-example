## Running Server and Client Applications:

### 1. First of all, start the RMI registry using the following command:

"rmiregistry &", where **'&'** is for running the process in **background**

### 2. Run the Server Application, using the following command:

"java -cp ./:~/public_html/classes/compute.jar \
-Djava.rmi.server.codebase=file:/Users/ackchatomar239/public_html/classes/compute.jar \
-Djava.rmi.se.hostname=ackchats-macbook-air.local \
-Djava.security.policy=./engine/engine.policy \
engine.ComputeEngine"

**-cp** is for specifying class-path for the java program.

**codebase** to specify the directory where the class-to-download are stored.

**hostname** to specify the name of the host, which in our case is **'ackchats-macbook-air.local'**, which I know by using the **"hostname"** command.

**policy** to specify the policy file for the security manager, usually stored in the package for client/server.

**<Fully-Qualified Class-Name>** to specify the class whose **main** has to be run, usually of the form **<package>.<class_name>**


### 3. Run the Client Application, using the following command:

"java -cp .:/Users/ackchatomar239/public_html/classes/compute.jar \
-Djava.rmi.server.codebase=file:/Users/ackchatomar239/public_html/classes/ \
-Djava.security.policy=./client/client.policy \
client.ComputePi ackchats-macbook-air.local 45"

In this command for client-side deployment, we pass as-arguments:

(1) **Name of server-host**: 'ackchats-macbook-air.local';
(2) **Precision number**: Number of significant numbers for pi value calculation, in decimal; 
