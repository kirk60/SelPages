# Field Factory

The Class SPFieldFactory is used to generate SPFields.

From a text string it returns an array of SPFields (see **Why Return An Array** below)

The General format for defining a field is

name="type[,identifyingData1[..]]"

The simplest instances of this are where a field is a simple field, that can be easily identified.
EG Those identified by class, css ,id ,name, text, tag, xpath 

In these cases the format is 
name="class,className[,expected text in field]

Some examples are
1. **classTest="class,classTest,classText"**
1. **cssTest="css,body > span:nth-child(5) > ul > li,cssText"**
1. **idTest="id,idTest,idText"**
1. **nameTest="name,nameTest,nameText"**
1. **parttextTest="parttext,parttext,parttextText"**
1. **tagTest="tag,button,tagText"**
1. **textTest="text,textText,textText"**
1. **xpathTeat="xpath,/html/body/span[8]/span,xpathText"**


## Why Return an array

In the examples so far each item only returns 1 element, so why return an array.

In a real world example a systems I was testing had an identical menu at the top and bottom of the page.

I wanted SPFields for each of these, I could have easily generated 2 items, however a simpler
solution was something like :

back="tbMenu,1,Y,back"

this generated 2 SPFields backMenuTop and backMenuBottom, 
1. **1** is used in the XPath identifier (which was the same for all menu items)
1. **Y** means it is required
1. **back** was the text expected on the menu

 