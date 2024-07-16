# Hi! This is my project for the 2024 Juni-Hackathon.

Summer Store is an application I created using JavaFX, GSON, and the eBay API.
The main focus of this application is to serve as a bare-bones platform for browsing flowers.
It uses JavaFX to create a GUI, the eBay Browse API to get data for different flower products,
and GSON to parse that data into useable JSON.

# Project Structure

## Node Components.

To somewhat organize my code, I've created a tree-like structure, in which the application 
is managed. This structure has nodes made of two parts: the "Node" class, 
and the "Layer" class.

The "Layer" class is responsible for defining the GUI components. I designed the "Layer" class to 
be easily extensible, that way you can easily add different functionality. Every "Layer" 
contains a GridPane, which is used to control the layout of the application. 
"Layer" also contains three methods of note:

* init()          : is where GUI components are defined.

* style()         : optional method to style the GUI components.

* onDataPassed()  : is called whenever data is passed to the "Layer".


The "Node" class is responsible for communication between different nodes in the tree. 
Certain methods allow you to manipulate and send data to, different nodes. 
Some notable methods are:

* initApplicationNodes() : Initializes node and its children.

* passDataToParent()     : Passes data to the parent node.

* passDataToPeer()       : Passes data to peer Nodes. (Nodes that share the same parent)

* passDataToChild()      : Passes data to a child Node.


## How the tree manages nodes.

Now for the tree itself. Each node is given a String identifier, that way it can be found 
when using "getChild()" or "passDataToChild()" methods. Then specify which of the parent's
grid cells the node should occupy. Finally, you can optionally specify if the node should be
enabled.

For a node to be enabled, it has to be visible and managed by JavaFX's layout manager.
when you have different menus, you likely want to switch which node is enabled, that way both
menus aren't rendered and taking up space at the same time.


## Tree root, and node initialization.

The tree root, defined as a class called "TreeBasedApplication", is the 
root of the application. The root itself is a node, but it is also responsible for 
initializing the application. When the application is initialized, 
it recursively goes through the tree, and initializes any node that is "enabled".

The process of initializing each node goes as follows: 

* First, call the "init()" and "style()" methods.

* Second, store the return value of the "init" method, this will later be passed to the node's 
children.

* Third, iterate through the node's children and pass the return value of "init()" to the child
as long as the child is "enabled".

* Fourth, repeat steps 1-3 until all "enabled" nodes have been initialized.


## eBay Oauth2 API.

eBay's APIs require an application key to make requests. The thing about application 
keys are that they are only valid for 2 hours, which means I needed a way to ensure that I always 
had a valid application key, which is where minting comes in.

To mint an application key, I needed to store my eBay App ID, Cert ID, Dev ID, and the 
redirect URI somewhere I could access with code. So I decided to use Replit's "Secrets" feature.
This allows me to keep that information hidden from users, while still being able to access it 
in my code.

Using that static information, I can use eBay's Oauth2 library to retrieve an application key.
I pulled this process into its own class called "EbayOauth2Api" under the api.ebay package.


## eBay Browse API.

Now that I have a valid application key, I can use the Browse API. using it just requires me to 
pass the application key as an HTTP header, and then define the query, offset, and limit in
the URL. I pulled that process into its own class called "HttpUtil".

Once the Browse API returns with the requested data, I can now parse it using GSON, and turn it 
into Java objects that I can work with. Now I can take the necessary data and put it into the 
"ProductInfo" class. This class stores the product title, price, and image URL. it also has 
multiple subclasses for storing the description and additional images.

I pulled this process into its own class called "EbayBrowseApi" under the api.ebay package.


# The development process.

The first few days were spent figuring out a basic plan for how I wanted my application to work, 
as well as getting familiar with JavaFX. While figuring this out, I decided to switch to using Maven,
as the basic JavaFX template provided by Replit wasn't going to cut it. I needed a way to add more 
dependencies as needed.

I hadn't really used Maven all that much, as I usually used Gradel for Java projects. This made 
setting it up pretty difficult, as I was still learning my way around Replit's environment. Once 
I got Maven set up, I had a few problems caused by my not defining the package in each file. After 
I got those issues sorted out, adding dependencies was really easy, and I could organize my project
better.

After finishing up with Maven, I began working on the actual structure of my application. My first 
idea was to use FXML, an XML-based way to create JavaFX GUIs. Unfortunately, this did not work for 
reasons I am still uncertain of, it's possible I imported things wrong, or maybe it just wasn't allowed
by Replit's environment. This is what led me to create my own application structure. I wanted it to
be tree-based like XML, and also be flexible.

The first implementation was only made up of very basic nodes, with the "Node" storing references 
to a parent node, the node's children, and its "Layer" component. It was lacking a reliable way to 
initialize the structure. Shortly after the first implementation, I changed the tree structure,
giving the root node its own unique class extending "Node". It was here that I implemented the first
tree traversal algorithm to initialize all the nodes.

Around this time, I was also experimenting with eBay's Browse API. I had to learn how HTTP requests
work in Java and also set up an eBay developer account so I could get the necessary authentication.
I realized shortly after that I couldn't just store the application key as a Secret, as it is only 
valid for 2 hours. So that's when I came across the process of "minting". Luckily, eBay had a Java 
library that handled this process for me, all I needed to do was pass the necessary credentials. 
Using this library, I could create a YAML string containing my App ID, Dev ID, Cert ID, and Redirect 
URI, and then pass this into a method that will retrieve an application key.

Now that I always had a valid application key, I began work on actually using the data I was getting.
I created a class to store the product information I wanted and then began work on parsing the JSON 
data. I used GSON to parse the JSON string into usable Java objects. I then went through the data
and found the information I wanted to use, and stored it in the class I defined earlier.

Now that I had the data, I needed to display it. So added some code to display the data in a card-like 
format. I then began work on a product-specific menu that would display the product description, additional 
images, etc. However, I ran into a problem. I couldn't enable or disable other menus or send data between them. 
The first problem I fixed was the ability to enable/disable different layers. This was done by using JavaFX's 
ability to hide and stop managing GUI components. Since each layer has a "GridPane" used to manage GUI components, 
I could just modify it and have it affect all of the GUI components. I then created some methods to swap between 
menus by switching which nodes were enabled.

Then I fixed the inability to send data between nodes. I used generics to determine what data type a Layer 
would accept, and then pass that data type whenever menus were switched. This had multiple problems, 
I couldn't easily pass data when the nodes were first initialized, and I could only pass one data type.



