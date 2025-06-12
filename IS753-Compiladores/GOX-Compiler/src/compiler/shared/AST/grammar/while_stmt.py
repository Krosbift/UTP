from dataclasses import dataclass
from ..nodes.statement_node import Statement


@dataclass
class WhileStmt(Statement):
    """
    ### 'while' expression '{' statement* '}'
    """

    def __init__(self, condition, body):
        self.condition = condition
        self.body = body
