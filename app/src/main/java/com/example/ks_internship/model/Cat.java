package com.example.ks_internship.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Cat implements Parcelable {

    private String name;
    private String breed;
    private String color;
    private String gender;
    private double age;

    public Cat(String name, String breed, String color, String gender, double age) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Breed: " + breed + "\n" +
                "Color: " + color + "\n" +
                "Gender: " + gender + "\n" +
                "Age: " + age + " y.o.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        return age == cat.age &&
                name.equals(cat.name) &&
                breed.equals(cat.breed) &&
                color.equals(cat.color) &&
                gender.equals(cat.gender);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + breed.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + (int) age;
        return result;
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(breed);
        dest.writeString(color);
        dest.writeString(gender);
        dest.writeDouble(age);
    }

    protected Cat(Parcel in) {
        name = in.readString();
        breed = in.readString();
        color = in.readString();
        gender = in.readString();
        age = in.readDouble();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
