## Running Server and Client Applications:

### *Codebase Setup:

Ensure that there is a **'public_html'** directory at the home-directory, as this helps the RMI communication protocol to find the code-to-download when needed.
Moreover, for this application, we create a **'classes'** subdirectory that contains: **compute.jar** & **Pi.class**; i.e. .class files for the same.


### 1. Start the RMI registry using the following command:

```
	rmiregistry &
```

Where **'&'** is for running the process in **background**

#### Note : Run rmiregistry from the directory which has visibility to the classes that needs to be downloaded, which in this case can be -
(1) **Current-directory**: because of '*compute*' folder;
(2) **Home directory**: because of '*compute.jar*' in *~/public_html/classes* folder;


### 2. Run the Server Application, using the following command:

```
	java -cp ./:~/public_html/classes/compute.jar \
	-Djava.rmi.server.codebase=file:/Users/ackchatomar239/public_html/classes/compute.jar \
	-Djava.rmi.server.hostname=ackchats-macbook-air.local \
	-Djava.security.policy=./engine/engine.policy \
 	engine.ComputeEngine
```

**-cp** is for specifying class-path for the java program. Try to provide absolute paths.

**codebase** to specify the directory where the class-to-download are stored. prefix - '**file:**', and path ends with '**/**'.

**hostname** to specify the name of the host, which in our case is **'ackchats-macbook-air.local'**, which I know by using ```hostname``` command.

**policy** to specify the policy file for the security manager, usually stored in the package for client/server.

**class-name**, fully-qualified, to specify the class whose **main** has to be run, usually of the form **<package>.<class_name>**


### 3. Run the Client Application, using the following command:

```
	java -cp .:/Users/ackchatomar239/public_html/classes/compute.jar \
	-Djava.rmi.server.codebase=file:/Users/ackchatomar239/public_html/classes/ \
	-Djava.security.policy=./client/client.policy \
	client.ComputePi ackchats-macbook-air.local 45
```

In this command for client-side deployment, we pass as-arguments:

(1) **Name of server-host**: 'ackchats-macbook-air.local';
(2) **Precision number**: Number of significant numbers for pi value calculation, in decimal; 
