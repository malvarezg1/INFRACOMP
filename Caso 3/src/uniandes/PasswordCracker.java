package uniandes;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PasswordCracker {

 public static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
 public static final int PASSWORD_MAX_LENGTH = 7;
 public static long START_TIME;
 
 public static int numCeros = 24/4;
 public final static String CADENA_INICIAL = "cadena inicial abc";
 public final static String algoritmo = "SHA-512";


 public static byte[] hexStringToByteArray(String s) {
  int len = s.length();
  byte[] data = new byte[len / 2];
  for (int i = 0; i < len; i += 2) {
   data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) +
    Character.digit(s.charAt(i + 1), 16));
  }
  return data;
 }
 
 public static void main(String[] args) throws NoSuchAlgorithmException {
  int cores = Runtime.getRuntime().availableProcessors();
  int lengthSet = PASSWORD_MAX_LENGTH / cores; // Cuantos d�gitos le asigno a cada core
  int end = 0;

  START_TIME = System.currentTimeMillis();

  ExecutorService executorService = Executors.newFixedThreadPool(cores);

  while (end < PASSWORD_MAX_LENGTH) {
   int start = end + 1;
   end = start + lengthSet;

   if (end > PASSWORD_MAX_LENGTH)
    end = PASSWORD_MAX_LENGTH;

   executorService.submit(new Cracker(start, end, algoritmo));
  }

  executorService.shutdown();

 }
 
}

