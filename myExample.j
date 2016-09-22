.class public myExample
.super java/lang/Object

;---puti---
.method public static puti(I)V
.limit stack 3
.limit locals 2
getstatic      java/lang/System/out Ljava/io/PrintStream;
iload 0
invokevirtual  java/io/PrintStream/println(I)V
return
.end method

;---main---
.method public static main([Ljava/lang/String;)V
.limit stack 5
.limit locals 5
invokestatic myExample/f()V
return
.end method


;---g---
.method public static fg()V
.limit stack 5
.limit locals 5
ldc 5
ldc 5
ldc 5
imul
iadd
invokestatic myExample/puti(I)V
return
.end method


;---h---
.method public static fh()V
.limit stack 5
.limit locals 5
ldc 100
ldc 5
idiv
invokestatic myExample/puti(I)V
invokestatic myExample/fg()V
return
.end method


;---f---
.method public static f()V
.limit stack 5
.limit locals 5
ldc 1
ldc 0
ior
istore 2
ldc 1
istore 0
iload 0
ldc 1
iadd
istore 0
ldc 4
ldc 5
if_icmpgt LabelC0
ldc 0
goto LabelC1
LabelC0:
ldc 1
LabelC1:
ifeq Label1
ldc 1
invokestatic myExample/puti(I)V
goto Label0
Label1:
ldc 11
ldc 12
if_icmpgt LabelC2
ldc 0
goto LabelC3
LabelC2:
ldc 1
LabelC3:
ifeq Label3
ldc 3
invokestatic myExample/puti(I)V
goto Label2
Label3:
ldc 8
ldc 9
if_icmpgt LabelC4
ldc 0
goto LabelC5
LabelC4:
ldc 1
LabelC5:
ifeq Label4
ldc 4
invokestatic myExample/puti(I)V
goto Label2
Label4:
iload 2
ifeq Label2
ldc 89
invokestatic myExample/puti(I)V
Label2:
ldc 2
invokestatic myExample/puti(I)V
ldc 0
istore 0
LabelF0:
iload 0
ldc 5
if_icmplt LabelC6
ldc 0
goto LabelC7
LabelC6:
ldc 1
LabelC7:
ifeq LabelF1
goto LabelS0
LabelS1:
iload 0
ldc 1
iadd
istore 0
goto LabelF0
LabelS0:
iload 0
invokestatic myExample/puti(I)V
goto LabelS1
LabelF1:
Label0:
return
.end method
