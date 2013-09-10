/**
 * Copyright (c) 2013 Jonathan McCluskey and William Harding.
 *
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE. 
 */

package org.bjwumpusandcompany.wumpusworld.common.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author Jonathan McCluskey
 *
 */
public class AbstractSubject<T> implements SubjectInterface<T> {

	private List<ObserverInterface<T>> observers = new ArrayList<ObserverInterface<T>>();
	
	/* (non-Javadoc)
	 * @see org.bjwumpusandcompany.wumpusworld.common.observer.SubjectInterface#registerObserver(org.bjwumpusandcompany.wumpusworld.common.observer.ObserverInterface)
	 */
	@Override
	public void registerObserver(ObserverInterface<T> observer) {
		observers.add(observer);
	}

	/* (non-Javadoc)
	 * @see org.bjwumpusandcompany.wumpusworld.common.observer.SubjectInterface#removeObserver(org.bjwumpusandcompany.wumpusworld.common.observer.ObserverInterface)
	 */
	@Override
	public void removeObserver(ObserverInterface<T> observer) {
		int i = observers.indexOf(observer);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	/* (non-Javadoc)
	 * @see org.bjwumpusandcompany.wumpusworld.common.observer.SubjectInterface#notifyObservers()
	 */
	@Override
	public void notifyObservers(T subjectData) {
		for(ObserverInterface<T> observer : observers) {
			observer.update(subjectData);
		}

	}

}
