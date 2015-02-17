#### Things I learn while doing homework

##### Lists

Create a new list & initialize values at the same time

```Java
List<String> listName = Array.asList("First", "Second", "Third");
```
BUT LISTS ARE IMMUTABLE.

Just make a list
```Java
 List<String> listname = new ArrayList<String>();
```

##### Iterate forwards & backwards
Take some list/set/array and iterate through it

```Java
ListIterator yourIterator = yourList.listIterator(yourList.size());

//Iterate from beginning to end
while(yourIterator.hasNext()) {
  System.out.println(yourIterator.next());
}

//Iterate from end to beginning
while(yourIterator.hasPrevious()) {
  System.out.println(yourIterator.previous());
}
```

##### Character arrays
Access a character at a certain index in the array like : `chararray[i];`

##### Random integer
`int randomnumber = (int)Math.ceil(Math.random() * 100);`
