
package csi403; 

import java.io.Serializable;
import java.util.List; 

public class Person implements Serializable {
    private String lastName;
    private String firstName;
    private List<Person> friends; 
    public Person() { } 
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public List<Person> getFriends() { return friends; }
    public void setFriends(List<Person> friends) { this.friends = friends; } 
}














