package ru.myandroidhelper.myaccountant.model;

public class ExpensesModel {
    private int id;
    private String nameExp;
    private String  expenses;
    private String date;
    private String categoryExp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameExp() {
        return nameExp;
    }

    public void setNameExp(String nameExp) {
        this.nameExp = nameExp;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategoryExp() {
        return categoryExp;
    }

    public void setCategoryExp(String categoryExp) {
        this.categoryExp = categoryExp;
    }
}
