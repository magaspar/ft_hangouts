package com.ft_hangouts;

public class CContact
{
    private long id;
    private String LastName;
    private String Name;
    private String Phone;
    private String Email;
    private String Adress;


    public CContact(Integer id, String LastName, String Name, String Phone, String Email, String Adress)
    {
        this.id = id;
        this.LastName = LastName;
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.Adress = Adress;
    }

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }


    public String getLastName()
    {
        return LastName;
    }
    public void setLastName(String LastName)
    {
        this.LastName = LastName;
    }



    public String getName()
    {
        return Name;
    }
    public void setName(String LastName)
    {
        this.Name = Name;
    }




    public String getPhone()
    {
        return Phone;
    }
    public void setPhone(String Phone)
    {
        this.Phone = Phone;
    }




    public String getEmail()
    {
        return Email;
    }
    public void setEmail(String Email)
    {
        this.Email = Email;
    }




    public String getAdress()
    {
        return Adress;
    }
    public void setAdress(String Adress)
    {
        this.Adress = Adress;
    }

}
