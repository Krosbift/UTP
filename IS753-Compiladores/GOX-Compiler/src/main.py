import argparse
from .compiler.compiler import Compiler
import sys


class Main:
    @staticmethod
    def init():
        args = Main.parse_arguments()
        filename = sys.argv[1]
        if not args.path_file:
            raise NameError("No file path was provided for compilation")
        Compiler(args.path_file, filename)

    @staticmethod
    def parse_arguments():
        parser = argparse.ArgumentParser(description="gox-compiler")
        parser.add_argument("path_file", help="File to compile")
        return parser.parse_args()
