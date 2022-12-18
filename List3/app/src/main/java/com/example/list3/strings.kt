package com.example.list3

fun id_name(id: String): String
{
    return id.toString() + "_name"
}

fun id_name(id: Int?): String
{
    return id.toString() + "_name"
}

fun id_content(id: String): String
{
    return id.toString() + "_content"
}

fun id_content(id: Int?): String
{
    return id.toString() + "_content"
}