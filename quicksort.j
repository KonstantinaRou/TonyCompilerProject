.class public quicksort
.super java/lang/Object
.method public static puti(I)V
.limit stack 3
.limit locals 2
getstatic      java/lang/System/out Ljava/io/PrintStream;
iload 0
invokevirtual  java/io/PrintStream/println(I)V
return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 15
.limit locals 15
invokestatic quicksort/qsort_aux()V
invokestatic quicksort/qsort_aux()V
.end method
invokestatic quicksort/qsort_aux()V
.end method
invokestatic quicksort/puts()V
invokestatic quicksort/puts()V
invokestatic quicksort/puti(I)V
invokestatic quicksort/puts()V
.end method
ldc65
ldc0
ldc16
ldc1
ldc137
ldc220
ldc101
invokestatic quicksort/writeList()V
invokestatic quicksort/qsort()V
invokestatic quicksort/writeList()V
.end method
