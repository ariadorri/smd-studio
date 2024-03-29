/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package uk.ac.sheffield.dcs.smdStudio.product.diagram.smd;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * The bean info for the SimpleModuleNode type.
 */
public class ComplexModuleNodeBeanInfo extends SimpleBeanInfo {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.BeanInfo#getPropertyDescriptors()
	 */
	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor nameDescriptor = new PropertyDescriptor("name",
					ComplexModuleNode.class);
			nameDescriptor.setValue("priority", new Integer(1));
			PropertyDescriptor descriptionDescriptor = new PropertyDescriptor(
					"description", ComplexModuleNode.class);
			descriptionDescriptor.setValue("priority", new Integer(2));
			PropertyDescriptor idDescriptor = new PropertyDescriptor("id",
					ComplexModuleNode.class, "getSMDId", "setSMDId");
			idDescriptor.setValue("priority", new Integer(3));
			idDescriptor.setHidden(true);
			return new PropertyDescriptor[] { nameDescriptor,
					descriptionDescriptor, idDescriptor, };
		} catch (IntrospectionException exception) {
			return null;
		}
	}
}
