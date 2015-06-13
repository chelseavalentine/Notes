# Chapter 8: Typing Math Formulas

### Writing basic formulas

LaTeX has 3 modes: `paragraph mode`, `left-to-right mode`, `math mode`. In math mode, letters are treated as symbols and are italicized.

Examples of how you'd write formulas:

```latex
\documentclass{article}
\begin{document}
\section*{Quadratic equations}
\begin{equation} % produces a numbered equation
    \label{quad}
    ax^2 + bx + c = 0
\end{equation}
where \(a, b \) and \(c \) are consonants. % small pieces of math are surrounded by \(... \) or $expression$

\begin{equation}
    \label{root}
    x_{1,2} = \frac{-b \pm \sqrt{b^2-4ac}}{2a}.
\end{equation}

\[ % unnumbered equation
    \Delta = b^2 - 4ac
\]

Look at equation (\ref{root}).
```

Also, a math environment:

```latex
\begin{math}
expression
\end{math}
```

#### Displaying formulas

For displayed formulas that need centering:

```latex
% this
\begin{displaymath}
expression
\end{displaymath}

% or this (best choice)
\[
expression
\]

% or this
$$
expression
$$
```


#### Numbering equations

This is like displaymath, but numbered.

```latex
\begin{equation}
    \label{key}
    expression
\end{equation}
```


#### Adding subscripts and superscripts

```latex
{expression}_{subscript}

{expression}^{superscript}
```


#### Extracting roots

```latex
\sqrt[order]{value}
```

You use the order if there're roots of higher order, and you need to specify that;


#### Writing fractions

```latex
\frac{numerator}{denumerator} % for larger fractions; you can do (a+b)/2
```


#### Greek letters

Surely, there's a reference too. And if you don't want italicized Greek letters, but instead want upright Greek letters, you can use the `upgreek` package.


#### Script letters

````latex
\mathcal{UPPERCASE LETTER}
````


#### Producing an ellipsis

* centered ellipsis: `\cdot`, `\cdots`
* diagonal ellipsis: `\ddots
* vertical ellipsis: `\vdots`


#### Changing the font, style, and size

* roman `\mathrm{}`
* italic `\mathit{}`
* bold `\boldmath{}` & unbold `\unboldmath{}`
    - you can use an `\mbox` w/ a bold command inside if you want to bold a small part of the text
* sans-serif `\mathsf{}`
* normal `\mathnormal{}`


#### Four math styles

* display `\displaystyle` (default for displayed formulas)
* text `\textstyle` (default for in-text formulas)
* script `\scriptstyle` (subscripts & superscripts)
* scriptscript `\scriptscriptstyle` (nested script style)


#### Customizing displayed formulas

* `\fleqn` displayed formulas are left-aligned
* `\leqno` all numbered formulas have their numbers on the left instead of the right

#### Define multiple equations at once

This'll define one numbered equation.

```latex
\begin{multiline}
\sum = a + b + c \\
    + d + e + f \\
    + g
\end{multiline}
```

First line is left-aligned, last line is right-aligned, and the rest are centered.

There'll be 2 numbered equations here:

```latex
\begin{gather} % equations are centered
    x + y + z = 0 \\
    y - z = 1
\end{gather}

% equations are aligned as you specify with an &
\begin{align}
    x + y + z &= 0 \\
    y - z &= 1
\end{align}
```


#### Aligning multi-line equations

* `flalign`: like align, but w/ more than 1 column, so columns are flushed left or right, respectively
* `alignat`: align at several places, marked by `&`
* `split`: like align, but used w/i another math environment, so it's unnumbered


#### Numbering rows in multi-line formulas

Each line is numbered as an equation in multi-line environments. You can stop this by pputting `\notag` before the end of the line.

Use `align*` or `gather*`, et al. to avoid numbering completely


#### Inserting text into formulas

* `\text{words}`
* `\intertext{text}` suspends the formula & the text follows in a separate paragraph, and then the multi-line formula is resumed.


#### Using operators

Predefined operators:

* `\arccos`, `\arcsin`, `\arctan`, `\arg`, `\cos`, `\cosh`
* `\cot`, `\coth`, `\scs`, `\deg`, `\det`, `\dim`
* `\exp`, `\gcd`, `\hom`, `\inf`, `\ker`, `\lg`
* `\lim`, `\liminf`, `\limsup`, `\ln`, `\log`, `\max`
* `\min`, `\Pr`, `\sec`, `\sin`, `\sinh`, `\sup`, `\tan`, `\tanh`
* modulo functions: `\bmod` for binary relations, or `\pmod{argument}` for modulos in parentheses


#### Binary operation symbols

Most relevant ones as of right now:

* `\cap`, `\cup`, `\vee`, `\wedge`
* `\div`, `\times`
* `\odot`, `\ominus`, `\oplus`, `\oslash`, `\otimes`
* `\pm`


#### Binary relation symbols

Most relevant ones as of right now:

* `\approx`, `\equiv`
* `\parallel`, `\perp`


#### Inequality relation symbols

* greater than or equal to `\geq`; `\leq`
* not equal to `\neq`


#### Subset and superset symbols

* `\subset`, `\subseteq` (has a line underneath)
* `\supset`, `\supseteq`


#### Variable sized operators

Used in sums, products, and set operations.

* `\bigcap`, `\bigcup`, `\bigvee`, `\bigwedge`
* `\prod`, `\sum`
* `\int`
* `\bigodot`, `\bigoplus`, `\bigotimes`


#### Arrows

Used for implications, maps, or descriptive expressions.

* `\downarrow`, `\Downarrow` (Arrow w/ parallel sides), `\uparrow`, `\Uparrow`
* `\updownarrow`, `\Updownarrow`
* `\leftarrow`, `\Leftarrow`, `\rightarrow`, `\Rightarrow`
* `\leftrightarrow`, `\Leftrightarrow`
* `\longleftarrow`, `\Longleftarrow`, `\longrightarrow`, `\Longrightarrow`
* `\longleftrightarrow`, `\Longleftrightarrow`
* `\mapsto`, `\Longmapsto`


#### Symbols derived from letters

* `\exists`, `\forall`, `\in` (element of), `\ni` (element of, mirrored)


#### Variable sized delimiters

Errr... you can just look them up.


#### Writing units

There've been packages made to make it easier to write units that look like units: `units`, `fancyunits`, `siunits`, `siunitx`.

Without those packages, you'll end up having to type `10\, \mathrm{m}` when you want to say 10m.


### Building math structures

#### Creating arrays

Array with round brackets:

```latex
\begin{math}
\[
    A = \left(
       \begin{array}{cc}
        a_{11} & a_{12} \\
        a_{21} & a_{22}
        \end{array}
      \right)
\]
\end{math}
```

You can create matrices easier with packages like `amsmath`, which typeset your matrices for you.


#### Writing binomial coefficients

```latex
\binom{n}{k} = \frac{n!}{k!(n-k)!}
```


### Stacking expressions

#### Underlining and overlining

* `\overline{argument}`
* `\underline{argument}`
* `\overbrace{argument}_thingthatgoesover`
* `\underbrace{argument}_thingthatgoesunder`


#### Setting accents

Add accents to variables with accent commands.

* `\bar{letter}`, `\vec{a}`, `\hat{a}`, `\widehat{abc}`


#### Putting a symbol above another

* `\underset{expressionbelow}{expression}` subscript size used below
* `\overset{expressionabove}{expression}` superscript size used above


#### Writing theorems and definitions

Defining a theorem environment:

```latex
\newtheorem{thm}{Theorem}
```

Declaring a definition environment:

```latex
\newtheorem{dfn}[thm]{Definition}
```

The optional argument is for stating an existing environment to share the numbering with. The package `amsthm` provides more customization & a proof environment.



# Chapter 9: Using Fonts

### Choosing the main font


