#!/bin/bash

if [ $# -eq 0 ]
then
  echo "you ran the script without providing command-line arguments."
  exit 0
fi

case $1 in
  --help|-h)
      echo "You requested help on this command."
      exit 0
  ;;
  --version|-v)
      echo "Version: 0.0.1"
      exit 0
  ;;
  -*) 
      echo "Invalid option."
      exit 33
  ;;
esac
