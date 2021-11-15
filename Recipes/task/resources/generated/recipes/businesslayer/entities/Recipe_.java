package recipes.businesslayer.entities;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Recipe.class)
public abstract class Recipe_ {

	public static volatile ListAttribute<Recipe, String> directions;
	public static volatile SingularAttribute<Recipe, User> author;
	public static volatile SingularAttribute<Recipe, String> name;
	public static volatile SingularAttribute<Recipe, LocalDateTime> lastChangeDate;
	public static volatile SingularAttribute<Recipe, String> description;
	public static volatile ListAttribute<Recipe, String> ingredients;
	public static volatile SingularAttribute<Recipe, Integer> id;
	public static volatile SingularAttribute<Recipe, String> category;

	public static final String DIRECTIONS = "directions";
	public static final String AUTHOR = "author";
	public static final String NAME = "name";
	public static final String LAST_CHANGE_DATE = "lastChangeDate";
	public static final String DESCRIPTION = "description";
	public static final String INGREDIENTS = "ingredients";
	public static final String ID = "id";
	public static final String CATEGORY = "category";

}

