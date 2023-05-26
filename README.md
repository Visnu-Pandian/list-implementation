# list-implementation

Objectives:
  • To gain experience with ArrayLists.
  • To gain experience with LinkedLists.
  • To gain experience with DoublyLinkedLists.
  • To gain experience with Test Driven Development.

Overview: Lists in Java are versatile data structures for storing collections of objects. This project will involve implementations of ArrayList, SinglyLinkedList and DoublyLinkedList. JUnit tests will be used to ensure the funcationality of these data structures.

ArrayList - A generic list collection that is backed by an object array that is stored in contiguous memory locations. In addition to an object array, the list should have a constant DEFAULT_CAPACITY of 10 elements and integer fields for its length and capacity. The underlying array must be able to expand when new items are added to it that would exceed the maximum capacity. When this happens, the array should grow by the default size.

LinkedList - A generic list collection that is backed by nodes which point to the next node in the list. In addition to the nodes, the list should implement an Iterator() class which returns an Itr() object.

DoublyLinkedList - A generic list collection that is backed by nodes which point to both the previous and next node in the list. Nodes should be accessible from other nodes in both directions. In addition, the list should implement a ListIterator() class and an Iterator() class, both of which return an Itr() object which can access nodes in both directions.
