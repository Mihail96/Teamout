package mk.ukim.finki.mihail.risteski.teamout.model.enumeration;

import javassist.NotFoundException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Enumeration
{
    protected Enumeration(Long id, String name){
        Id = id;
        Name = name;
    }

    protected Long Id;

    protected String Name;

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public static <T extends Enumeration> List<T> All(T enumeration)
    {
        Class<?> thisClass = enumeration.getClass();
        Field[] fields = thisClass.getDeclaredFields();
        List<T> fieldValues = new ArrayList<>();

        for (Field field : fields)
        {
            int fieldModifier = field.getModifiers();

            if (Modifier.isPublic(fieldModifier) &&
                    Modifier.isStatic(fieldModifier) &&
                    Modifier.isFinal(fieldModifier))
            {
                try
                {
                    fieldValues.add((T)field.get(field));
                }
                catch (Exception e)
                {

                }
            }
        }

        return fieldValues;
    }

    public static <T extends Enumeration> T GetById(T e, Long id) throws NotFoundException
    {
        List<T> enumerations = All(e);

        for (T enumeration: enumerations)
        {
            if(enumeration.getId().equals(id))
            {
                return enumeration;
            }
        }

        throw new NotFoundException("Couldn't find the desired entity");
    }

    public static <T extends Enumeration> T GetByName(T e, String name) throws NotFoundException
    {
        List<T> enumerations = All(e);

        for (T enumeration: enumerations)
        {
            if(enumeration.getName().equals(name))
            {
                return enumeration;
            }
        }

        throw new NotFoundException("Couldn't find the desired entity");
    }
}
