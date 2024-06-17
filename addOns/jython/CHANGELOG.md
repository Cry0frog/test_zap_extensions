# Changelog
All notable changes to this add-on will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## Unreleased
### Changed
- Update minimum ZAP version to 2.15.0.

## [15] - 2024-04-11
### Changed
- Maintenance changes.
- Update Active and Passive Script Templates to include a `getMetadata` function. This will allow them to be used as regular scan rules.
- Depend on the `commonlib` add-on for scan rule scripts.
- Update minimum `scripts` add-on version to 45.1.0.

## [14] - 2023-12-19
### Changed
- Update minimum ZAP version to 2.14.0.
- Move "Jython" under "Scripts > Engine" in the Options panel list.

### Fixed
- Remove the script engine when the add-on is uninstalled.

## [13] - 2023-09-07
### Changed
- Update minimum ZAP version to 2.13.0.
- Replace usage of singletons with injected variables (e.g. `model`, `control`) in scripts.

### Fixed
- Updated encode-decode script templates to conform to the latest method signatures.
- Update the content-length header field after setting the request body in the authentication template.

## [12] - 2021-10-07
### Added
- encode-decode default and rot13 templates.

### Changed
- Update links to zaproxy repo.
- Rename reliability to confidence in active/passive templates.
- Maintenance changes.
- Update minimum ZAP version to 2.11.0.

## [11] - 2020-12-15
### Added
- Add info and repo URLs.

### Changed
- Update minimum ZAP version to 2.10.0.
- Update Jython from 2.7.1 to 2.7.2.
- Update the help to mention the bundled Jython version.
- Jython templates now includes an extender script (getInputsFromuser.py) for setting global script variables based on user input.

### Fixed
- Fix link in a script template.

## 10 - 2018-05-08

- Correctly set path module defined in the options and address UI hang (Issue 4651).
- Minor tweak in extender template.
- Add default template for Script Input Vector.
- Add help page for the options.

## 9 - 2018-01-19

- Update Passive Rule template to include new function.

## 8 - 2017-11-27

- Updated for 2.7.0.

## 7 - 2017-10-27

- Do not initialise java.awt.Toolkit when in daemon.
- Update HTTP Sender template with initiator ID of AJAX Spider.
- Added extender template and example.

## 6 - 2017-09-20

- Update Jython from 2.5.3 to 2.7.1

## 5 - 2017-01-10

- add the python module path interface

## 4 - 2015-04-13

- Updated for ZAP 2.4

## 3 - 2014-04-10

- Moved to beta
- Changed help file structure to support internationalisation (Issue 981).
- Added content-type to help pages (Issue 1080).
- Updated add-on dir structure (Issue 1113).

## 2 - 2013-10-01

- Added help and extra templates

## 1 - 2013-09-30



[15]: https://github.com/zaproxy/zap-extensions/releases/jython-v15
[14]: https://github.com/zaproxy/zap-extensions/releases/jython-v14
[13]: https://github.com/zaproxy/zap-extensions/releases/jython-v13
[12]: https://github.com/zaproxy/zap-extensions/releases/jython-v12
[11]: https://github.com/zaproxy/zap-extensions/releases/jython-v11