package edu.olya.tour.model;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

//todo все энтити модели должны иметь default constructor, методы toString & hashCode, и быть сериализуемыми. Зачем каждый из этих пунктов, попробуй сама до этого дойти - вопрос на сеобеседовании может быть
public class Comment {
    long id;
    private String author;
    private Date date;
    private String comment;

    private Comment() {}

    public Comment(String author, Date date, String comment) {
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    public Comment(long id, String author, Date date, String comment) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.comment = comment;
    }

    //todo - все методы parse вынеси в отдельный утилитный класс, например ObjectBuilder, после этого устрани дублирование кода
    public static Comment parse(Map<String, String[]> parameterMap)
            throws NumberFormatException, IllegalAccessException, ParseException {

        Comment comment = new Comment();

        for (Map.Entry<String, String []> param : parameterMap.entrySet()) {
            String paramName = param.getKey();

            String paramValue = Arrays.toString( param.getValue());
            paramValue = paramValue.substring(1, paramValue.length() - 1);

            Field field = null;
            try {
                field = Comment.class.getDeclaredField(paramName);
            } catch (NoSuchFieldException e) {
                //throw new Exception("Unknown argument name: " + paramName);
                continue;
            }
            Class fieldType = field.getType();

            PropertyEditor pe = PropertyEditorManager.findEditor(fieldType);
            if (pe == null) {
                if (fieldType == Date.class && paramValue.length() > 0) {
                    field.set(comment, new SimpleDateFormat("yyyy-MM-dd").parse(paramValue));
                }
            } else if(paramValue.length() > 0) {
                pe.setAsText(paramValue);
                field.set(comment, pe.getValue());
            }
//            } catch (NumberFormatException | IllegalAccessException | ParseException e) {
//                //throw new ValidationException("Invalid data type " + paramName + " = " + paramValue + ", but required " + fieldType.getCanonicalName());
//            }
        }
        return comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
