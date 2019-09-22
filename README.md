# RFLCS
Solutions to Repetition Free Longest Common Subsequence, an APX-hard problem


## Abstract
\textit{Repetition Free Longest Common Subsequence} is a measure of similarity between pairs of strings, introduced by S. S. Adi et al. \cite{adi}, proposed as an inference method for the search for homologous gene sequences. Calculating this measure is an \texttt{APX}-hard problem and, to date, we have not found an approximation algorithm with a constant performance ratio.

This result justifies the use of combinatorial optimization techniques for problem solving, such as the \textit{meta-heuristics}. The meta-heuristic frameworks are resolution methodologies that are abstracted from the specific problem, outlining research procedures within a generic solution space.
This thesis work presents the realisation of three different meta-heuristics that exploit a new \textit{greedy} selection criterion, whose goodness is verified experimentally. The algorithms are afterwards subjects of comparison with the current state of the art and with an approximation algorithm, for which a family of instances is exhibited which leads to the worst possible solutions.

The overall results show methods that can compute competitive solutions, in short waiting times. The latter is a fundamental requirement in application contexts where the amount of data is huge and/or the size of individual instances is hardly sustainable for a modern computer.
