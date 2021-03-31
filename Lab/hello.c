#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>


pthread_mutex_t lock;


pthread_mutex_t mutex;;
// Variable compartida a modificar
int counter;
// Función a ser ejecutada por el pthread1
void *myThreadFun1(void *vargp)
{
pthread_mutex_lock(&lock); 

 counter += 1;

pthread_mutex_unlock(&lock);

 printf("\n Job %d has started\n", counter);

 sleep(5);

 printf("\n Job %d has finished\n", counter);

 return NULL;
}
// Función a ser ejecutada por el pthread2
void *myThreadFun2(void *vargp)
{
pthread_mutex_lock(&lock); 
 counter += 1;
pthread_mutex_unlock(&lock);

 printf("\n Job %d has started\n", counter);

 sleep(5);

 printf("\n Job %d has finished\n", counter);

 return NULL;
}
int main()
{
pthread_mutex_init(&lock, NULL);

 pthread_t t1;
 pthread_t t2;
 pthread_create(&t1, NULL, myThreadFun1, (void *)&t1);
 pthread_create(&t2, NULL, myThreadFun2, (void *)&t2);

 pthread_join(t1, NULL);
 pthread_join(t2, NULL);
 printf("final value: %d\n", counter);
 pthread_exit(NULL);

}
