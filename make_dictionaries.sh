#!/bin/bash
for i in {1..3000}
do
   echo `openssl rand -hex 12` >> app/package-dictionary.txt
   echo `openssl rand -hex 13` >> app/class-dictionary.txt
   echo `openssl rand -hex 14` >> app/method-dictionary.txt
done

