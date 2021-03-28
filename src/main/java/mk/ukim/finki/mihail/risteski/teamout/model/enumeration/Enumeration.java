package mk.ukim.finki.mihail.risteski.teamout.model.enumeration;

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

    public static <T extends Enumeration> Optional<T> GetById(T e, Long id)
    {
        List<T> enumerations = All(e);

        for (T enumeration: enumerations)
        {
            if(enumeration.getId().equals(id))
            {
                return Optional.of(enumeration);
            }
        }

        return Optional.empty();
    }

    public static <T extends Enumeration> Optional<T> GetByName(T e, String name)
    {
        List<T> enumerations = All(e);

        for (T enumeration: enumerations)
        {
            if(enumeration.getName().equals(name))
            {
                return Optional.of(enumeration);
            }
        }

        return Optional.empty();
    }
}
