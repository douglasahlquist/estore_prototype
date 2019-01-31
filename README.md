
ESTORE PROJECT

1. A general architecture for a distributed, scalable system that would serve as a backend for a hypothetical webstore and shopping cart type application. The requirements would be that individual users would have records of what was purchased and their properties, and be able to return to an existing cart of items if they had a cart that wasn't emptied between sessions by check-out.


2. Code using a bare-bones version of this shopping backend cart and the purchase history component as a 'Proof of Concept. It must understand that different products have certain fixed properties (name, price), and many specific properties (ie, t-shirt sizes, color of hat, flavor of candy, etc). 


The code will be implemented in an on going process such that it will be modular enough to easily add new products or integrate with various frontends. It should also have the ability for a specified user to return to an existing cart of items, then update the user's purchase history and emptying the cart on check-out.


Details will be forthcoming to specify reasoning behind why the implementation this prototype was done in a certain way and if it differed from the earlier description, and what choices were made when deciding what to implement and what to stub out.  Some features such as Payment and User Authentication will be either omitted of stubbed out.


PROJECT ORGANIZATION

This repo contains serveral 'maven' projects.  There organization are in a heirarchical structure such that all may be build from the 'estore_master' project.


BUILD

  You must have JAVA8 and Maven installed on your system to build the current repo.  Navigate into the estore_master project and run the `mvn install` command to build all archives.
As of now no java mains are moved into the repo.  SpringBoot and Restful Controllers will follow shortly.
