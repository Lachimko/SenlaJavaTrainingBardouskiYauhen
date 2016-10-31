package menu.program.menuprocessor;

import menu.program.actions.parents.IAction;
import menu.program.menuelements.IMenu;

public interface IMenuBuilder {
	
	public IMenu createRoot(String rootCaption);
	
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent, IAction actionWhichWillExecute);
	
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent);

	public IMenu buildMenu();

}
