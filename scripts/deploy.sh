#!/bin/sh

# unused, but could be useful for the future

# test for required input filename
if [ ! -r "$1" ]; then
    printf "error: insufficient input or file not readable.  Usage: %s property_file\n" "$0"
    exit 1
fi

# `IFS` = delimiter
# `read` = reads line, delimited by IFS, and assigns variables in order
while IFS='=' read -r key value; do
    if [ "$key" == "version" ]; then
        version="$value"
    fi
done < "$1"

echo $version
