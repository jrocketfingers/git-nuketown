package com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.strategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.BuzzStrategyFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.BuzzStringPrinterFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.FizzBuzzOutputGenerationContextVisitorFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.FizzStrategyFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.FizzStringPrinterFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.IntegerIntegerPrinterFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.NewLineStringPrinterFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.factories.NoFizzNoBuzzStrategyFactory;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.visitors.FizzBuzzOutputGenerationContext;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.interfaces.printers.StringPrinter;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.interfaces.strategies.OutputGenerationStrategy;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.interfaces.strategies.SingleStepOutputGenerationParameter;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.interfaces.visitors.OutputGenerationContext;
import com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.interfaces.visitors.OutputGenerationContextVisitor;

@Service
public class SingleStepOutputGenerationStrategy implements OutputGenerationStrategy {

	private final List<OutputGenerationContext> contexts;
	private final OutputGenerationContextVisitor contextVisitor;
	private final StringPrinter myNewLinePrinter;

	/**
	 * @param fizzBuzzOutputGenerationContextVisitorFactory
	 * @param fizzStrategyFactory
	 * @param fizzStringPrinterFactory
	 * @param buzzStrategyFactory
	 * @param buzzStringPrinterFactory
	 * @param noFizzNoBuzzStrategyFactory
	 * @param integerIntegerPrinterFactory
     * @param newLineStringPrinterFactory
     */
	@Autowired
	public SingleStepOutputGenerationStrategy(
			final FizzBuzzOutputGenerationContextVisitorFactory fizzBuzzOutputGenerationContextVisitorFactory,
			final FizzStrategyFactory fizzStrategyFactory,
			final FizzStringPrinterFactory fizzStringPrinterFactory,
			final BuzzStrategyFactory buzzStrategyFactory,
			final BuzzStringPrinterFactory buzzStringPrinterFactory,
			final NoFizzNoBuzzStrategyFactory noFizzNoBuzzStrategyFactory,
			final IntegerIntegerPrinterFactory integerIntegerPrinterFactory,
			final NewLineStringPrinterFactory newLineStringPrinterFactory) {
		this.contextVisitor = fizzBuzzOutputGenerationContextVisitorFactory.createVisitor();
		this.contexts = new ArrayList<OutputGenerationContext>();
		this.contexts.add(new FizzBuzzOutputGenerationContext(fizzStrategyFactory.createIsEvenlyDivisibleStrategy(),
				fizzStringPrinterFactory.createStringPrinter()));
		this.contexts.add(new FizzBuzzOutputGenerationContext(buzzStrategyFactory.createIsEvenlyDivisibleStrategy(),
				buzzStringPrinterFactory.createStringPrinter()));
		this.contexts.add(new FizzBuzzOutputGenerationContext(
				noFizzNoBuzzStrategyFactory.createIsEvenlyDivisibleStrategy(),
				integerIntegerPrinterFactory.createPrinter()));

		this.myNewLinePrinter = newLineStringPrinterFactory.createStringPrinter();
	}

	/**
	 * @param generationParameter
	 * @return
     */
	public void performGenerationForCurrentStep(final SingleStepOutputGenerationParameter generationParameter) {
		final int nGenerationParameter = generationParameter.retrieveIntegerValue();
		final Iterator<OutputGenerationContext> iterator = this.contexts.iterator();
		while (iterator.hasNext()) {
			final OutputGenerationContext context = iterator.next();
			this.contextVisitor.visit(context, nGenerationParameter);
		}
		this.myNewLinePrinter.print();
	}
}
