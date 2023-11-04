/*El presente programa toma un archivo, el cual puede ya existir el en ordeador, o en caso contrario lo creará como un nuevo archivo, y también recibe un texto ingresado por el usuario, el cual pega en el archivo y lo gaurda allí*/

% Permite ejecutar el programa automáticamente: 
:- initialization(main).

%Regla principal del programa:
main :-
    write('Ingrese el nombre del archivo de texto: '),
    read_line_to_string(user_input, FileName),
    open(FileName, append, Stream),
    write('Ingrese el texto que desea guardar (Escriba "fin" para finalizar):\n'),
    write_text(Stream),
    close(Stream),
    write('Texto guardado en el archivo '), write(FileName), write('.\n').

%Regla recursiva que sirve para escribir texto en el archivo:
write_text(Stream) :-
    read_line_to_string(user_input, Line),
    (Line = "fin" -> true ; (
        writeln(Stream, Line),
        write_text(Stream)
    )).